package com.example.movie_theater.services;

import com.example.movie_theater.dtos.BookingDTO;
import com.example.movie_theater.dtos.BookingHoldRequestDTO;
import com.example.movie_theater.dtos.UserDTO;
import com.example.movie_theater.entities.Booking;

import java.util.List;


public interface BookingService {
    List<Booking> getAllBookings();
    BookingDTO getBookingById(Long id);
    List<BookingDTO> getBookingByUser(Long userId);
    Booking createBooking(Booking booking);
    BookingDTO holdSeats(BookingHoldRequestDTO request, Long userId);
    void cancelExpiredBookings();
    void deleteBooking(Long id);
    boolean isSeatBooked(Long seatId, Long showtimeId);


    void updateBookingStatus(Long bookingId, String newStatus);

    String placeBooking(Long bookingId);

}
