package com.example.movie_theater.services.impl;

import com.example.movie_theater.dtos.ShowtimeDTO;
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

    @Override
    public List<ShowtimeDTO> getShowtimes(Long movieId, Long cinemaId){
        List<Showtime> showtimes = showtimeRepository.findByMovieIdAndCinemaId(movieId, cinemaId);

        return showtimes.stream().map(showtime -> {
            int duration = showtime.getMovie().getDuration();
            return new ShowtimeDTO(
                    showtime.getId(),
                    showtime.getMovie().getId(),
                    showtime.getMovie().getTitle(),
                    showtime.getHall().getId(),
                    showtime.getHall().getName(),
                    showtime.getStartTime(),
                    showtime.getStartTime().plusMinutes(duration),
                    showtime.getBasePrice()
            );
        }).toList();
    }
}
