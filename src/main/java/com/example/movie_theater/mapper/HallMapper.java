package com.example.movie_theater.mapper;

import com.example.movie_theater.dtos.HallDTO;
import com.example.movie_theater.entities.Hall;
import com.example.movie_theater.entities.Theater;

import java.util.ArrayList;

public class HallMapper {
    public static HallDTO toDTO(Hall hall) {
        return new HallDTO(
                hall.getId(),
                hall.getName(),
                hall.getTotalSeats(),
                hall.getTheater().getId(),
                hall.getTheater().getName()
        );
    }
    public static Hall toEntity(HallDTO dto, Theater theater) {
        return new Hall(
                dto.getId(),
                dto.getName(),
                dto.getTotalSeats(),
                new ArrayList<>(),
                theater,
                new ArrayList<>()
        );
    }
}

