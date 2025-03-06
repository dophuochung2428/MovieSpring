package com.example.movie_theater.repositories;

import com.example.movie_theater.entities.Movie;
import com.example.movie_theater.entities.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Long> {
    List<Theater> findByLocationContainingIgnoreCase(String location);
}
