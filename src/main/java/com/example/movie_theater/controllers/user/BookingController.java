package com.example.movie_theater.controllers.user;

import com.example.movie_theater.dtos.BookingDTO;
import com.example.movie_theater.dtos.BookingHoldRequestDTO;
import com.example.movie_theater.dtos.PaymentDTO;
import com.example.movie_theater.dtos.PaymentInfoDTO;
import com.example.movie_theater.entities.Booking;
import com.example.movie_theater.services.BookingService;
import com.example.movie_theater.services.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;
    @Autowired
    private PaymentService paymentService;

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

    @PostMapping("/hold-and-book/{userId}")
    public ResponseEntity<Map<String, String>> holdAndPlaceBooking(
            @RequestBody BookingHoldRequestDTO holdRequestDTO,
            @PathVariable Long userId,
            HttpServletRequest req){
        try {
            BookingDTO bookingDTO = bookingService.holdSeats(holdRequestDTO, userId);
            String paymentUrl;
            try{

                PaymentInfoDTO paymentDTO = paymentService.createPayment(req, bookingDTO.getPrice(), bookingDTO.getId());
                paymentUrl = paymentDTO.getURL();
            } catch (UnsupportedEncodingException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(Collections.singletonMap("error", "Payment processing failed"));
            }
            Map<String, String> response = new HashMap<>();
            response.put("paymentUrl", paymentUrl);

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", e.getMessage()));

        }
    }

}
