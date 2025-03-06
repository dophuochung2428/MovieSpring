package com.example.movie_theater.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "seat_templates")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class SeatTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String seatType; // 🔥 VD: VIP, STANDARD, COUPLE

    @Column(nullable = false)
    private double priceMultiplier; // 🔥 VD: 1.5 cho VIP, 1.0 cho Standard
}

