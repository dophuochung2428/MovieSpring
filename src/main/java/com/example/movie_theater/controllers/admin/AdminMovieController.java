package com.example.movie_theater.controllers.admin;


import com.example.movie_theater.dtos.MovieDTO;
import com.example.movie_theater.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/movies")
public class AdminMovieController {
    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieDTO> createMovie(@RequestBody MovieDTO movieDTO) {
        if (movieDTO == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(movieService.saveMovie(movieDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> updateMovie(@PathVariable Long id, @RequestBody MovieDTO movieDTO){
        if (movieDTO == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(movieService.updateMovie(id, movieDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id){
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}
