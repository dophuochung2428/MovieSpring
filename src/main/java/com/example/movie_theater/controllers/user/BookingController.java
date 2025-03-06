package com.example.movie_theater.controllers.user;

import com.example.movie_theater.dtos.BookingDTO;
import com.example.movie_theater.entities.Booking;
import com.example.movie_theater.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping("/create")
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        return ResponseEntity.ok(bookingService.createBooking(booking));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> getMBookingById(@PathVariable Long id){
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> getBookingsByUser(@PathVariable Long userId) {
        List<Booking> bookings = bookingService.getBookingByUser(userId);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/check-seat")
    public boolean isSeatBooked(@RequestParam Long seatId, @RequestParam Long showtimeId) {
        return bookingService.isSeatBooked(seatId, showtimeId);
    }
}
