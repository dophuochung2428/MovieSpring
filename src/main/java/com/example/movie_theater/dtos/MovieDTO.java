package com.example.movie_theater.dtos;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class MovieDTO {
    private Long id;
    private String title;
    private String genre;
    private String director;
    private String producer;  // Nhà sản xuất
    private String country;   // Quốc gia
    private int duration;     // Thời lượng (phút)
    private String description; // Mô tả
    private String cast; // Dàn diễn viên
    private LocalDate releaseDate;
    private List<ImageDTO> images; // Danh sách ảnh
}
