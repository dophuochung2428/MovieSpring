package com.example.movie_theater.repositories;

import com.example.movie_theater.entities.SeatTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatTemplateRepository extends JpaRepository<SeatTemplate, Long> {
}
