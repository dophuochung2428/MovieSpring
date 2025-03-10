package com.example.movie_theater.services.impl;

import com.example.movie_theater.dtos.BookingDTO;
<<<<<<< Updated upstream
import com.example.movie_theater.entities.Booking;
=======
import com.example.movie_theater.dtos.BookingHoldRequestDTO;
import com.example.movie_theater.entities.*;
>>>>>>> Stashed changes
import com.example.movie_theater.mapper.BookingMapper;
import com.example.movie_theater.repositories.BookingRepository;
import com.example.movie_theater.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
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
<<<<<<< Updated upstream
=======

    @Override
    public void updateBookingStatus(Long bookingId, String newStatus) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(()-> new RuntimeException("Booking not found"));
        BookingStatus bookingStatus = BookingStatus.valueOf(newStatus.toUpperCase());
        booking.setBookingStatus(bookingStatus);
        bookingRepository.save(booking);
    }

    @Override
    public String placeBooking(Long bookingId) {
        return "";
    }


//    @Transactional
//    @Override
//    public String placeBooking(Long bookingId) {
//        Booking booking = bookingRepository.findById(bookingId)
//                .orElseThrow(() -> new RuntimeException("Booking not found"));
//
//        // Kiểm tra booking có hết hạn không
//        if (booking.getExpirationTime().isBefore(LocalDateTime.now())) {
//            throw new RuntimeException("Booking has expired");
//        }
//
//        // Cập nhật trạng thái booking thành "WAITING_FOR_PAYMENT"
//        booking.setBookingStatus(BookingStatus.WAITING_FOR_PAYMENT);
//        bookingRepository.save(booking);
//
//        // Tạo URL thanh toán VNPay
//        return vnpayService.createPaymentUrl(booking);
//    }
>>>>>>> Stashed changes
}
