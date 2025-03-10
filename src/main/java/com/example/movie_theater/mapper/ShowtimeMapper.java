package com.example.movie_theater.mapper;

import com.example.movie_theater.dtos.ShowtimeDTO;
import com.example.movie_theater.entities.Hall;
import com.example.movie_theater.entities.Movie;
import com.example.movie_theater.entities.Showtime;

public class ShowtimeMapper {
    public static ShowtimeDTO toDTO(Showtime showtime) {
        return new ShowtimeDTO(
                showtime.getId(),
                showtime.getMovie().getId(),
                showtime.getMovie().getTitle(),
                showtime.getHall().getId(),
                showtime.getHall().getName(),
                showtime.getStartTime(),
                showtime.getStartTime().plusMinutes(showtime.getMovie().getDuration()),
                showtime.getBasePrice()
        );
    }
    public static Showtime toEntity(ShowtimeDTO dto, Movie movie, Hall hall) {
        return new Showtime(
                dto.getId(),
                movie,
                hall,
                dto.getStartTime(),
                dto.getBasePrice()
        );
    }
}

