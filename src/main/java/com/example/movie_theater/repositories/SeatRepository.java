package com.example.movie_theater.repositories;

import com.example.movie_theater.entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByHallId(Long hallId);
    @Query("SELECT s FROM Seat s WHERE s.id IN :seatIds AND s.hall.id = :hallId")
    List<Seat> findByIdInAndHallId(@Param("seatIds") List<Long> seatIds, @Param("hallId") Long hallId);

}
