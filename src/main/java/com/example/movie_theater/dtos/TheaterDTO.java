package com.example.movie_theater.dtos;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class TheaterDTO {
    private Long id;
    private String name;
    private String location;
    private int totalHalls;
}

