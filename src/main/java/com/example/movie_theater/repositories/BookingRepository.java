package com.example.movie_theater.repositories;

import com.example.movie_theater.entities.Booking;
import com.example.movie_theater.entities.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserId(Long userId);

    @Query("SELECT COUNT(bs) > 0 FROM BookingSeat bs WHERE bs.seat.id = :seatId AND bs.booking.showtime.id = :showtimeId")
    boolean isSeatBooked(@Param("seatId") Long seatId, @Param("showtimeId") Long showtimeId);

    List<Booking> findByBookingStatusAndBookingTimeBefore(BookingStatus status, LocalDateTime time);

}
