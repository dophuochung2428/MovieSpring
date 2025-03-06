package com.example.movie_theater.mapper;

import com.example.movie_theater.dtos.SeatDTO;
import com.example.movie_theater.entities.Hall;
import com.example.movie_theater.entities.Seat;
import com.example.movie_theater.entities.SeatTemplate;

public class SeatMapper {
    public static SeatDTO toDTO(Seat seat) {
        return new SeatDTO(
                seat.getId(),
                seat.getHall().getName(),
                seat.getSeatNumber(),
                seat.getSeatTemplate().getId()
        );
    }
    public static Seat toEntity(SeatDTO dto, Hall hall, SeatTemplate seatTemplate) {
        return new Seat(
                dto.getId(),
                hall,
                dto.getSeatNumber(),
                seatTemplate
        );
    }
}

