package com.example.movie_theater.dtos;

import com.example.movie_theater.entities.BookingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class BookingDTO {
    private Long id;
    private String username;
    private LocalDateTime showtime;
    private List<String> seatNumbers;
    private double price;
    private LocalDateTime bookingTime;
    private BookingStatus bookingStatus;
}

