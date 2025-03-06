package com.example.movie_theater.dtos;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SeatDTO {
    private Long id;
    private String seatNumber;
    private String seatType;
    private double priceMultiplier;
}

