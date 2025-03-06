package com.example.movie_theater.services;

import com.example.movie_theater.dtos.HallDTO;
import com.example.movie_theater.dtos.MovieDTO;
import com.example.movie_theater.dtos.TheaterDTO;
import com.example.movie_theater.entities.Theater;

import java.util.List;
public interface TheaterService {
    List<TheaterDTO> getAllTheaters();
    List<TheaterDTO> findByLocation(String location);
    List<MovieDTO> getMovieInTheater(Long id);
    Theater getTheaterById(Long id);
    TheaterDTO saveTheater(TheaterDTO theaterDTO);
    TheaterDTO updateTheater(Long id, TheaterDTO theaterDTO);
    TheaterDTO createTheaterWithHall(TheaterDTO theaterDTO);
    List<HallDTO> getHallsByTheater(Long theaterId);
    void deleteTheater(Long id);
    void checkName(Long id, String name);
}
