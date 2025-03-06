package com.example.movie_theater.controllers.user;

import com.example.movie_theater.entities.Seat;
import com.example.movie_theater.services.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
public class SeatController {
    @Autowired
    private SeatService seatService;

    @GetMapping
    public ResponseEntity<List<Seat>> getAllSeats(){
        List<Seat> seats = seatService.getAllSeats();
        return ResponseEntity.ok(seats);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Seat> getSeatById(@PathVariable Long id){
        return ResponseEntity.ok(seatService.getSeatById(id));
    }

    @GetMapping("/hall/{hallId}")
    public ResponseEntity<List<Seat>> getSeatsByHall(@PathVariable Long hallId) {
        return ResponseEntity.ok(seatService.getSeatByHall(hallId));
    }
    @PostMapping
    public ResponseEntity<Seat> createSeat(@RequestBody Seat seat){
        if(seat == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(seatService.saveSeat(seat));
    }
//    @PutMapping("/{id}")
//    public ResponseEntity<Seat> updateSeat(@PathVariable Long id, @RequestBody Seat seat){
//        if(seat == null){
//            return ResponseEntity.badRequest().build();
//        }
//        Seat existingSeat = seatService.getSeatById(id);
//        existingSeat.setSeatNumber(seat.getSeatNumber());
//        existingSeat.setTheater(seat.getTheater());
//        return ResponseEntity.ok(seatService.saveSeat(existingSeat));
//    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeat(@PathVariable Long id){
        seatService.deleteSeat(id);
        return ResponseEntity.noContent().build();
    }
}
