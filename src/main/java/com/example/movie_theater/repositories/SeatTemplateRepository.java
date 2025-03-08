package com.example.movie_theater.repositories;

import com.example.movie_theater.entities.Seat;
import com.example.movie_theater.entities.SeatTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatTemplateRepository extends JpaRepository<SeatTemplate, Long> {

}
