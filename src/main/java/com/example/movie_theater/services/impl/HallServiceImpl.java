package com.example.movie_theater.services.impl;

import com.example.movie_theater.dtos.HallDTO;
import com.example.movie_theater.entities.Hall;
import com.example.movie_theater.entities.Theater;
import com.example.movie_theater.mapper.HallMapper;
import com.example.movie_theater.repositories.HallRepository;
import com.example.movie_theater.repositories.TheaterRepository;
import com.example.movie_theater.services.HallService;
import com.example.movie_theater.services.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HallServiceImpl implements HallService {
    @Autowired
    private HallRepository hallRepository;
    @Autowired
    private TheaterRepository theaterRepository;
    @Autowired
    private SeatService seatService;

    @Override
    public List<Hall> getAllHall() {
        return hallRepository.findAll();
    }

    @Override
    public Optional<Hall> getHallById(Long id) {
        return hallRepository.findById(id);
    }

    @Override
    public HallDTO saveHall(HallDTO hallDTO) {
        Theater theater = theaterRepository.findById(hallDTO.getTheaterId()).orElseThrow();
        Hall hall = HallMapper.toEntity(hallDTO, theater);
        return HallMapper.toDTO(hallRepository.save(hall));
    }

    @Override
    public void createHallForTheater(Theater theater) {
        for(int i = 1; i <= theater.getTotalHalls(); i++){
            Hall hall = new Hall();
            hall.setName("Hall "+ i);
            hall.setTotalSeats(120);
            hall.setTheater(theater);

            Hall hall1 = hallRepository.save(hall);
            seatService.createSeatsForHall(hall1);
        }
    }

    @Override
    public void deleteHall(Long id) {
        hallRepository.deleteById(id);
    }
}
