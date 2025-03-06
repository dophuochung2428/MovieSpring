package com.example.movie_theater.dtos;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class MovieDTO {
    private Long id;
    private String title;
    private String genre;
    private String director;
    private int duration;
    private String description;
    private LocalDateTime releaseDate;
}

