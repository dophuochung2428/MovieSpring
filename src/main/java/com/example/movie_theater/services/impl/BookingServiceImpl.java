package com.example.movie_theater.services.impl;

import com.example.movie_theater.dtos.BookingDTO;
import com.example.movie_theater.entities.Booking;
import com.example.movie_theater.mapper.BookingMapper;
import com.example.movie_theater.repositories.BookingRepository;
import com.example.movie_theater.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private  BookingRepository bookingRepository;

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public BookingDTO getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
        return BookingMapper.toDTO(booking);
    }

    @Override
    public List<Booking> getBookingByUser(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    @Override
    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public boolean isSeatBooked(Long seatId, Long showtimeId) {
        return false;
    }
}
