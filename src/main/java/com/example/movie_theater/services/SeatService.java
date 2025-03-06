package com.example.movie_theater.services;

import com.example.movie_theater.entities.Hall;
import com.example.movie_theater.entities.Seat;

import java.util.List;
public interface SeatService {
    List<Seat> getAllSeats();
    Seat getSeatById(Long id);
    List<Seat> getSeatByHall(Long hallId);
    Seat saveSeat(Seat seat);
    void createSeatsForHall(Hall hall);
    void deleteSeat(Long id);
}
