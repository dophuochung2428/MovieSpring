package com.example.movie_theater.services;

import com.example.movie_theater.dtos.ShowtimeDTO;
import com.example.movie_theater.entities.Showtime;

import java.util.List;
public interface ShowtimeService {
    List<Showtime> getAllShowtime();
    Showtime getShowtimeById(Long id);
    Showtime saveShowtime(Showtime showtime);
    void deleteShowtime(Long id);

    List<ShowtimeDTO> getShowtimes(Long movieId, Long cinemaId);
}
