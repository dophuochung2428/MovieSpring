package com.example.movie_theater.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HallDTO {
    private Long id;
    private String name;
    private int totalSeats;
    private Long theaterId;
    private String theaterName;

}

