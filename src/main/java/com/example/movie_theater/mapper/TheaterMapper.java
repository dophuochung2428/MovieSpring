package com.example.movie_theater.mapper;

import com.example.movie_theater.dtos.MovieDTO;
import com.example.movie_theater.dtos.TheaterDTO;
import com.example.movie_theater.entities.Movie;
import com.example.movie_theater.entities.Theater;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TheaterMapper {
    public static TheaterDTO toDTO(Theater theater) {
        return new TheaterDTO(
                theater.getId(),
                theater.getName(),
                theater.getLocation(),
                theater.getTotalHalls()
        );
    }

    public static List<TheaterDTO> toDTOList(List<Theater> theaters) {
        return theaters.stream()
                .map(TheaterMapper::toDTO)
                .collect(Collectors.toList());
    }
    public static Theater toEntity(TheaterDTO dto) {
        return new Theater(
                dto.getId(),
                dto.getName(),
                dto.getLocation(),
                dto.getTotalHalls(),
                new ArrayList<>()
        );
    }
}

