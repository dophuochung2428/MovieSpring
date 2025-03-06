package com.example.movie_theater.services;

import com.example.movie_theater.dtos.HallDTO;
import com.example.movie_theater.dtos.TheaterDTO;
import com.example.movie_theater.entities.Hall;
import com.example.movie_theater.entities.Theater;

import java.util.List;
import java.util.Optional;

public interface HallService {
    List<Hall> getAllHall();
    Optional<Hall> getHallById(Long id);
    HallDTO saveHall(HallDTO hallDTO);
    void createHallForTheater(Theater theater);
    void deleteHall( Long id);

}
