package com.example.movie_theater.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "booking_seats")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class BookingSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat seat;
}
