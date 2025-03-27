package com.example.movie_theater.repositories;

import com.example.movie_theater.entities.BookingSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingSeatRepository extends JpaRepository<BookingSeat, Long> {
    @Query("SELECT bs.seat.id FROM BookingSeat bs WHERE bs.booking.showtime.id = :showtimeId")
    List<Long> findBookedSeatsByShowtime(@Param("showtimeId") Long showtimeId);
}

