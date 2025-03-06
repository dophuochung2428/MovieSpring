package com.example.movie_theater.services;

import com.example.movie_theater.entities.SeatTemplate;

import java.util.List;

public interface SeatTemplateService {
    List<SeatTemplate> getAllSeatTemplate();
    SeatTemplate getSeatTemplateById(Long id);
    SeatTemplate saveSeatTemplate(SeatTemplate seatTemplate);
    void deleteSeatTemplateById(Long id);
}
