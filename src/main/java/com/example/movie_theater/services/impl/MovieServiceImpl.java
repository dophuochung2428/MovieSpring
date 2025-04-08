package com.example.movie_theater.services.impl;

import com.example.movie_theater.dtos.MovieDTO;
import com.example.movie_theater.dtos.TheaterDTO;
import com.example.movie_theater.entities.Movie;
import com.example.movie_theater.mapper.MovieMapper;
import com.example.movie_theater.repositories.MovieRepository;
import com.example.movie_theater.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private  MovieRepository movieRepository;

    @Override
    public List<MovieDTO> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return MovieMapper.toDTOList(movies);
    }

    @Override
    public MovieDTO getMovieById(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow();
        return MovieMapper.toDTO(movie);
    }

    @Override
    public MovieDTO updateMovie(Long id, MovieDTO movieDTO) {
        Movie existingMovie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found movie id to update"));
        existingMovie.setTitle(movieDTO.getTitle());
        existingMovie.setGenre(movieDTO.getGenre());
        existingMovie.setDuration(movieDTO.getDuration());

        return MovieMapper.toDTO(movieRepository.save(existingMovie));
    }

    @Override
    public MovieDTO saveMovie(MovieDTO movieDTO) {
        Movie movie = MovieMapper.toEntity(movieDTO);

        return MovieMapper.toDTO(movieRepository.save(movie));
    }

    @Override
    public void deleteMovie(Long id) {

        movieRepository.deleteById(id);
    }

    @Override
    public List<MovieDTO> searchMovies(String title) {
        List<Movie> movies = movieRepository.findByTitleContainingIgnoreCase(title);
        return MovieMapper.toDTOList(movies);
    }

    @Override
    public List<TheaterDTO> getTheaterByFilm(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow();
        return movie.getShowtimes().stream()
                .map(showtime -> showtime.getHall().getTheater())
                .distinct()
                .map(theater -> new TheaterDTO(
                        theater.getId(),
                        theater.getName(),
                        theater.getLocation(),
                        theater.getTotalHalls()
                ))
                .collect(Collectors.toList());
    }
}
