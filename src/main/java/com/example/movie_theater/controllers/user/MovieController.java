package com.example.movie_theater.controllers.user;

import com.example.movie_theater.dtos.MovieDTO;
import com.example.movie_theater.dtos.ShowtimeDTO;
import com.example.movie_theater.dtos.TheaterDTO;
import com.example.movie_theater.services.MovieService;
import com.example.movie_theater.services.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private ShowtimeService showtimeService;

    @GetMapping
    public ResponseEntity<List<MovieDTO>> getAllMovies() {
        List<MovieDTO> movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMovieById(@PathVariable Long id){
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieDTO>> searchMovies(@RequestParam String title) {

        return ResponseEntity.ok(movieService.searchMovies(title));
    }

    @GetMapping("/{movieId}/theaters")
    public ResponseEntity<List<TheaterDTO>> getTheaterByFilm(@PathVariable Long movieId) {
        List<TheaterDTO> theaterDTOS = movieService.getTheaterByFilm(movieId);
        return ResponseEntity.ok(theaterDTOS);
    }

    @GetMapping("/{movieId}/cinemas/{cinemaId}/showtimes")
    public ResponseEntity<List<ShowtimeDTO>> getShowtimeByTheater(@PathVariable Long movieId,
                                                                  @PathVariable Long cinemaId){
        List<ShowtimeDTO> showtimeDTOS = showtimeService.getShowtimes(movieId, cinemaId);
        return ResponseEntity.ok(showtimeDTOS);
    }

}
