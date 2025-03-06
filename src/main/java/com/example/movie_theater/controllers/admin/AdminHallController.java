package com.example.movie_theater.controllers.admin;

import com.example.movie_theater.entities.Hall;
import com.example.movie_theater.entities.Theater;
import com.example.movie_theater.mapper.TheaterMapper;
import com.example.movie_theater.services.HallService;
import com.example.movie_theater.services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/halls")
public class AdminHallController {
    @Autowired
    private HallService hallService;
    @Autowired
    private TheaterService theaterService;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHall(@PathVariable Long id){
        Hall hall = hallService.getHallById(id).orElseThrow();
        Theater theater = hall.getTheater();
        hallService.deleteHall(id);
        theater.setTotalHalls(theater.getTotalHalls()-1);
        theaterService.updateTheater(theater.getId(), TheaterMapper.toDTO(theater));
        return ResponseEntity.noContent().build();
    }
}
