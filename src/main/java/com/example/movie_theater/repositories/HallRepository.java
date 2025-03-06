package com.example.movie_theater.repositories;

import com.example.movie_theater.entities.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HallRepository extends JpaRepository<Hall, Long> {
    List<Hall> findByTheaterId(Long theaterId);
}
