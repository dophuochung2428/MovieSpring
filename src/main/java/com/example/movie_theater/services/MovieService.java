package com.example.movie_theater.services;


import com.example.movie_theater.dtos.MovieDTO;
import com.example.movie_theater.dtos.TheaterDTO;
import com.example.movie_theater.entities.Movie;

import java.util.List;
public interface MovieService {
    List<MovieDTO> getAllMovies();
    MovieDTO getMovieById(Long id);
    MovieDTO updateMovie(Long id, MovieDTO movieDTO);
    MovieDTO saveMovie(MovieDTO movieDTO);
    void deleteMovie(Long id);
    List<MovieDTO> searchMovies(String title);
    List<TheaterDTO> getTheaterByFilm(Long id);
}
