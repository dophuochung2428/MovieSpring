package com.example.movie_theater.controllers.user;

import com.example.movie_theater.dtos.MovieDTO;
import com.example.movie_theater.dtos.TheaterDTO;
import com.example.movie_theater.services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/theaters")
public class TheaterController {
    @Autowired
    private TheaterService theaterService;

    @GetMapping
    public ResponseEntity<List<TheaterDTO>> getAllTheaters() {
        List<TheaterDTO> theaters = theaterService.getAllTheaters();
        return ResponseEntity.ok(theaters);
    }

    @GetMapping("/location")
    public ResponseEntity<List<TheaterDTO>> getAllTheaterByLocation(@RequestParam String location) {
        List<TheaterDTO> theaters = theaterService.findByLocation(location);
        return ResponseEntity.ok(theaters);
    }

    @GetMapping("/{id}/movies")
    public ResponseEntity<List<MovieDTO>> getMovieInTheater(@PathVariable Long id) {
        List<MovieDTO> movies = theaterService.getMovieInTheater(id);
        return ResponseEntity.ok(movies);
    }


//    @GetMapping("/{id}")
//    public ResponseEntity<Theater> getTheaterById(@PathVariable Long id){
//        return ResponseEntity.ok(theaterService.getTheaterById(id));
//    }

}
