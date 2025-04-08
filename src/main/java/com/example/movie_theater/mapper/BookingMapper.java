package com.example.movie_theater.mapper;

import com.example.movie_theater.dtos.BookingDTO;
import com.example.movie_theater.dtos.TheaterDTO;
import com.example.movie_theater.entities.Booking;
import com.example.movie_theater.entities.Showtime;
import com.example.movie_theater.entities.Theater;
import com.example.movie_theater.entities.User;

import java.util.List;
import java.util.stream.Collectors;

public class BookingMapper {
    public static BookingDTO toDTO(Booking booking) {
        return new BookingDTO(
                booking.getId(),
                booking.getUser().getUsername(),
                booking.getShowtime().getStartTime(),
                booking.getBookingSeats().stream()
                        .map(bs -> bs.getSeat().getSeatNumber()).toList(),
                booking.getPrice(),
                booking.getBookingTime(),
                booking.getBookingStatus()
        );
    }
    public static List<BookingDTO> toDTOList(List<Booking> bookings) {
        return bookings.stream()
                .map(BookingMapper::toDTO)
                .collect(Collectors.toList());
    }
    public static Booking toEntity(BookingDTO bookingDTO, User user, Showtime showtime) {
        Booking booking = new Booking();
        booking.setId(bookingDTO.getId());
        booking.setUser(user);
        booking.setShowtime(showtime);
        booking.setPrice(bookingDTO.getPrice());
        booking.setBookingTime(bookingDTO.getBookingTime());
        booking.setBookingStatus(bookingDTO.getBookingStatus());
        return booking;
    }
}

