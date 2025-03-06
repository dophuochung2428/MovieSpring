package com.example.movie_theater.entities;

import java.time.LocalDateTime;
import java.util.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "movies")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String genre;

    private String director;
    private int duration; // Thời lượng (phút)
    private String description;
    private LocalDateTime releaseDate;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Showtime> showtimes = new ArrayList<>();
}
