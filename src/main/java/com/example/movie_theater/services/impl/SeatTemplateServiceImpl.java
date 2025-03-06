package com.example.movie_theater.services.impl;

import com.example.movie_theater.entities.SeatTemplate;
import com.example.movie_theater.repositories.SeatTemplateRepository;
import com.example.movie_theater.services.SeatTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatTemplateServiceImpl implements SeatTemplateService {
    @Autowired
    private SeatTemplateRepository seatTemplateRepository;
    @Override
    public List<SeatTemplate> getAllSeatTemplate() {
        return seatTemplateRepository.findAll();
    }

    @Override
    public SeatTemplate getSeatTemplateById(Long id) {
        return seatTemplateRepository.findById(id).orElse(null);
    }

    @Override
    public SeatTemplate saveSeatTemplate(SeatTemplate seatTemplate) {
        return seatTemplateRepository.save(seatTemplate);
    }

    @Override
    public void deleteSeatTemplateById(Long id) {
        seatTemplateRepository.deleteById(id);
    }
}
