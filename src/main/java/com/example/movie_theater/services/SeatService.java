package com.example.movie_theater.services;

import com.example.movie_theater.dtos.SeatDTO;
import com.example.movie_theater.entities.Hall;
import com.example.movie_theater.entities.Seat;

import java.util.List;
public interface SeatService {
    List<Seat> getAllSeats();
    Seat getSeatById(Long id);
    List<SeatDTO> getSeatByHall(Long hallId);
    Seat saveSeat(Seat seat);
    void createSeatsForHall(Hall hall);
    void deleteSeat(Long id);
}
