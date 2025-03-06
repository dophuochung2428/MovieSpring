package com.example.movie_theater.mapper;

import com.example.movie_theater.dtos.SeatTemplateDTO;
import com.example.movie_theater.entities.SeatTemplate;

public class SeatTemplateMapper {
    public static SeatTemplateDTO toDTO(SeatTemplate seatTemplate) {
        return new SeatTemplateDTO(
                seatTemplate.getId(),
                seatTemplate.getSeatType(),
                seatTemplate.getPriceMultiplier()
        );
    }
    public static SeatTemplate toEntity(SeatTemplateDTO dto) {
        return new SeatTemplate(
                dto.getId(),
                dto.getSeatType(),
                dto.getPriceMultiplier()
        );
    }
}

