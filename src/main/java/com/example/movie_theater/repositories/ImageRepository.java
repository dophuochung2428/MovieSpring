package com.example.movie_theater.repositories;

import com.example.movie_theater.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> { }

