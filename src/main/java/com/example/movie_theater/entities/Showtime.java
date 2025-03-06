package com.example.movie_theater.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "showtimes", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"hall_id", "startTime"}) // 🔥 Tránh trùng suất chiếu trong cùng 1 phòng
})
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Showtime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "hall_id", nullable = false)
    private Hall hall;

    @Column(nullable = false)
    private LocalDateTime startTime; // Format: "YYYY-MM-DD HH:MM:SS"
}
