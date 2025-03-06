package com.example.movie_theater.controllers.admin;

import com.example.movie_theater.dtos.HallDTO;
import com.example.movie_theater.dtos.TheaterDTO;
import com.example.movie_theater.entities.Hall;
import com.example.movie_theater.entities.Theater;
import com.example.movie_theater.mapper.HallMapper;
import com.example.movie_theater.mapper.SeatMapper;
import com.example.movie_theater.mapper.TheaterMapper;
import com.example.movie_theater.services.HallService;
import com.example.movie_theater.services.SeatService;
import com.example.movie_theater.services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/theaters")
public class AdminTheaterController {
    @Autowired
    private TheaterService theaterService;
    @Autowired
    private HallService hallService;
    @Autowired
    private SeatService seatService;

    @PostMapping
    public ResponseEntity<TheaterDTO> createTheater(@RequestBody TheaterDTO theaterDTO) {
        try {
            TheaterDTO theaterDTOSaved = theaterService.createTheaterWithHall(theaterDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(theaterDTOSaved);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @PostMapping("/{id}/halls")
    public ResponseEntity<HallDTO> createHallToTheater(@PathVariable Long id, @RequestBody HallDTO hallDTO){
        if (hallDTO.getTheaterId() == null) {
            hallDTO.setTheaterId(id);
        }

        theaterService.checkName(id, hallDTO.getName());

        Theater theater = theaterService.getTheaterById(id);

        HallDTO savedHall = hallService.saveHall(hallDTO);
        seatService.createSeatsForHall(HallMapper.toEntity(savedHall, theater));

        theater.setTotalHalls(theater.getTotalHalls()+1);
        theaterService.updateTheater(id, TheaterMapper.toDTO(theater));

        return ResponseEntity.ok(savedHall);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TheaterDTO> updateTheater(@PathVariable Long id, @RequestBody TheaterDTO theaterDTO){
        if (theaterDTO == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(theaterService.updateTheater(id, theaterDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTheater(@PathVariable Long id){
        theaterService.deleteTheater(id);
        return ResponseEntity.noContent().build();
    }



    @GetMapping("/{theaterId}/halls")
    public ResponseEntity<List<HallDTO>> getHallOfTheater(@PathVariable Long theaterId){
        List<HallDTO> hallDTOS = theaterService.getHallsByTheater(theaterId);
        return  ResponseEntity.ok(hallDTOS);
    }
}
