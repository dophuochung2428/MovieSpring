package com.example.movie_theater.controllers;

import com.example.movie_theater.entities.Booking;
import com.example.movie_theater.entities.BookingStatus;
import com.example.movie_theater.repositories.BookingRepository;
import org.hibernate.Hibernate;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;

import java.time.LocalDateTime;
import java.util.List;


@Component
public class BookingCleanupScheduler {
    private final BookingRepository bookingRepository;
    private static final Logger logger = LoggerFactory.getLogger(BookingCleanupScheduler.class);

    public BookingCleanupScheduler(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Transactional
    @Scheduled(cron = "0 * * * * ?") // Chạy mỗi phút
    public void cleanupExpiredBookings() {
        LocalDateTime expiryTime = LocalDateTime.now().minusMinutes(5);

        List<Booking> expiredBookings = bookingRepository.findByBookingStatusAndBookingTimeBefore(
                BookingStatus.PENDING, expiryTime
        );

        for (Booking booking : expiredBookings) {
            processBooking(booking);
        }

        logger.info("Đã xóa {} booking hết hạn.", expiredBookings.size());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void processBooking(Booking booking) {
        Hibernate.initialize(booking.getBookingSeats());
        booking.getBookingSeats().forEach(seat -> seat.setBooking(null)); // Hủy liên kết
        booking.getBookingSeats().clear();
        booking.setBookingStatus(BookingStatus.CANCELLED);
    }
}

