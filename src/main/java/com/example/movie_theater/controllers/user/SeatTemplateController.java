package com.example.movie_theater.controllers.user;

import com.example.movie_theater.entities.SeatTemplate;
import com.example.movie_theater.services.SeatTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/seat-templates")
public class SeatTemplateController {
    @Autowired
    private SeatTemplateService seatTemplateService;

    @GetMapping
    public ResponseEntity<List<SeatTemplate>> getAllSeatTemplates() {
        return ResponseEntity.ok(seatTemplateService.getAllSeatTemplate());
    }
}
