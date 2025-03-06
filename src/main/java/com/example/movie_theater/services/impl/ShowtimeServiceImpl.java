package com.example.movie_theater.services.impl;

import com.example.movie_theater.entities.Showtime;
import com.example.movie_theater.repositories.ShowtimeRepository;
import com.example.movie_theater.services.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowtimeServiceImpl implements ShowtimeService {
    @Autowired
    private ShowtimeRepository showtimeRepository;

    @Override
    public List<Showtime> getAllShowtime() {
        return showtimeRepository.findAll();
    }

    @Override
    public Showtime getShowtimeById(Long id) {
        return showtimeRepository.findById(id).orElse(null);
    }

    @Override
    public Showtime saveShowtime(Showtime showtime) {
        return showtimeRepository.save(showtime);
    }

    @Override
    public void deleteShowtime(Long id) {
        showtimeRepository.deleteById(id);
    }
}
