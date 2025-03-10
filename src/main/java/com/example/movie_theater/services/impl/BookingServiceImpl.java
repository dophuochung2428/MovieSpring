package com.example.movie_theater.services.impl;

import com.example.movie_theater.dtos.BookingDTO;

import com.example.movie_theater.dtos.BookingHoldRequestDTO;
import com.example.movie_theater.entities.*;

import com.example.movie_theater.dtos.UserDTO;

import com.example.movie_theater.mapper.BookingMapper;
import com.example.movie_theater.mapper.UserMapper;
import com.example.movie_theater.repositories.BookingRepository;
import com.example.movie_theater.repositories.SeatRepository;
import com.example.movie_theater.repositories.ShowtimeRepository;
import com.example.movie_theater.repositories.UserRepository;
import com.example.movie_theater.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {
    @Autowired
    private  BookingRepository bookingRepository;
    @Autowired
    private ShowtimeRepository showtimeRepository;
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private UserRepository userRepository;

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
    public BookingDTO holdSeats(BookingHoldRequestDTO request, Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        Showtime showtime = showtimeRepository.findById(request.getShowtimeId())
                .orElseThrow(() -> new RuntimeException("Showtime not found"));
        Long hallId = showtime.getHall().getId();

        List<Long> seatIds = request.getSeatIds();

        List<Seat> seats = seatRepository.findAllById(request.getSeatIds());

        List<Seat> validSeats = seatRepository.findByIdInAndHallId(seatIds, hallId);

        if (validSeats.size() != seatIds.size()) {
            throw new RuntimeException("Some seats do not belong to the hall of this showtime!");
        }

        long basePrice = showtime.getBasePrice();

        long totalPrice = (long) seats.stream()
                .mapToDouble(seat -> basePrice * seat.getSeatTemplate().getPriceMultiplier())
                .sum();

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setShowtime(showtime);
        booking.setPrice(totalPrice);
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setExpirationTime(LocalDateTime.now().plusMinutes(10));

        List<BookingSeat> bookingSeats = seats.stream()
                .map(seat -> new BookingSeat(booking, seat))
                .collect(Collectors.toList());

        booking.setBookingSeats(bookingSeats);

        bookingRepository.save(booking);
        return BookingMapper.toDTO(booking);
    }

    @Override
    public void cancelExpiredBookings() {

    }

    @Override
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public boolean isSeatBooked(Long seatId, Long showtimeId) {
        return false;
    }
  
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

}
