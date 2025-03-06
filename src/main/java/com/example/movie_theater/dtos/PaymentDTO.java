package com.example.movie_theater.dtos;

import com.example.movie_theater.entities.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PaymentDTO {
    private Long id;
    private Long bookingId;
    private double amount;
    private String paymentMethod;
    private PaymentStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

