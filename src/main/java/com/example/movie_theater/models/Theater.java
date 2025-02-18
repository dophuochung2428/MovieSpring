package com.example.movie_theater.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "theaters")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;
}
