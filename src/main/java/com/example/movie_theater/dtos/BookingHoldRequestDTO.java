package com.example.movie_theater.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookingHoldRequestDTO {
    private Long showtimeId;
    private List<Long> seatIds;
    private Long userId;
}
