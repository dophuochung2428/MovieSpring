package com.example.movie_theater.services.impl;

import com.example.movie_theater.entities.Hall;
import com.example.movie_theater.entities.Seat;
import com.example.movie_theater.entities.SeatTemplate;
import com.example.movie_theater.repositories.SeatRepository;
import com.example.movie_theater.repositories.SeatTemplateRepository;
import com.example.movie_theater.services.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {
    @Autowired
    private  SeatRepository seatRepository;
    @Autowired
    private SeatTemplateRepository seatTemplateRepository;


    @Override
    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    @Override
    public Seat getSeatById(Long id) {
        return seatRepository.findById(id).orElse(null);
    }

    @Override
    public List<Seat> getSeatByHall(Long hallId) {
        return  seatRepository.findByHallId(hallId);
    }

    @Override
    public Seat saveSeat(Seat seat) {

        return seatRepository.save(seat);
    }

    @Override
    public void createSeatsForHall(Hall hall) {
        int groupSize = 20;
        SeatTemplate seatTemplate = seatTemplateRepository.findById(1L).orElseThrow();
        for(int i = 0; i < hall.getTotalSeats(); i++){
            Seat seat = new Seat();
            char group = (char) ('A' + (i / groupSize));
            int num = (i % groupSize) + 1;
            seat.setSeatNumber(group + "" + num);
            seat.setHall(hall);
            seat.setSeatTemplate(seatTemplate);
            seatRepository.save(seat);
        }
    }

    @Override
    public void deleteSeat(Long id) {
        seatRepository.deleteById(id);
    }
}
