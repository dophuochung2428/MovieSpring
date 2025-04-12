package com.example.movie_theater.entities;

import java.time.LocalDate;
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
    private String producer; // nhà sàn xuất
    private String country;
    private int duration; // Thời lượng (phút)

    @Column(columnDefinition = "TEXT")
    private String description; //moo tả

    @Column(columnDefinition = "TEXT")
    private String cast;

    private LocalDate releaseDate;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Showtime> showtimes = new ArrayList<>();

    @OneToMany(mappedBy = "movie", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Image> images = new ArrayList<>(); // Danh sách ảnh & teaser
}
