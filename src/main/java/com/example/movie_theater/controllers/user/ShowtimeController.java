package com.example.movie_theater.controllers.user;

import com.example.movie_theater.entities.Showtime;
import com.example.movie_theater.services.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/showtime")
public class ShowtimeController {
    @Autowired
    private ShowtimeService showtimeService;

    @GetMapping
    public ResponseEntity<List<Showtime>> getAllShowtime() {
        return ResponseEntity.ok(showtimeService.getAllShowtime());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Showtime> getShowtimeById(@PathVariable Long id){
        return ResponseEntity.ok(showtimeService.getShowtimeById(id));
    }

    @PostMapping
    public ResponseEntity<Showtime> createShowtime(@RequestBody Showtime showtime) {
        if (showtime == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(showtimeService.saveShowtime(showtime));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShowtime(@PathVariable Long id){
        showtimeService.deleteShowtime(id);
        return ResponseEntity.noContent().build();
    }
}
