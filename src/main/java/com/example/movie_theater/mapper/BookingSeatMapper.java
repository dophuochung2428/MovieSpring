package com.example.movie_theater.mapper;

import com.example.movie_theater.dtos.BookingSeatDTO;
import com.example.movie_theater.entities.Booking;
import com.example.movie_theater.entities.BookingSeat;
import com.example.movie_theater.entities.Seat;

public class BookingSeatMapper {
    public static BookingSeatDTO toDTO(BookingSeat bookingSeat) {
        return new BookingSeatDTO(
                bookingSeat.getId(),
                bookingSeat.getBooking().getId(),
                bookingSeat.getSeat().getSeatNumber()
        );
    }
    public static BookingSeat toEntity(BookingSeatDTO dto, Booking booking, Seat seat) {
        return new BookingSeat(
                dto.getId(),
                booking,
                seat
        );
    }
}

