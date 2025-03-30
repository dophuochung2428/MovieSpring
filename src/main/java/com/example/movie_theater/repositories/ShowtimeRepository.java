package com.example.movie_theater.repositories;

import com.example.movie_theater.entities.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {
    List<Showtime> findByMovieId(Long movieId);
    List<Showtime> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);

    @Query("SELECT s FROM Showtime s WHERE s.hall.theater.id = :cinemaId AND s.movie.id = :movieId")
    List<Showtime> findByMovieIdAndCinemaId(@Param("movieId") Long movieId, @Param("cinemaId") Long cinemaId);

}