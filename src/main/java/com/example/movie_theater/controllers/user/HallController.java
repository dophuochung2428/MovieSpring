package com.example.movie_theater.controllers.user;

import com.example.movie_theater.entities.Hall;
import com.example.movie_theater.services.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/halls")
public class HallController {
    @Autowired
    private HallService hallService;

    @GetMapping
    public ResponseEntity<List<Hall>> getAllHalls() {
        return ResponseEntity.ok(hallService.getAllHall());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Hall>> getHallById(@PathVariable Long id) {
        return ResponseEntity.ok(hallService.getHallById(id));
    }
}
