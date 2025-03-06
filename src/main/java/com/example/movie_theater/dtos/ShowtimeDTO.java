package com.example.movie_theater.dtos;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ShowtimeDTO {
    private Long id;
    private Long movieId;
    private String movieTitle;
    private Long hallId;
    private String hallName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}

