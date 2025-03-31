package com.example.movie_theater.mapper;

import com.example.movie_theater.dtos.ImageDTO;
import com.example.movie_theater.dtos.MovieDTO;
import com.example.movie_theater.entities.Movie;

import java.util.List;
import java.util.stream.Collectors;

public class MovieMapper {
    public static MovieDTO toDTO(Movie movie) {
        return new MovieDTO(
                movie.getId(),
                movie.getTitle(),
                movie.getGenre(),
                movie.getDirector(),
                movie.getProducer(),  // Nhà sản xuất
                movie.getCountry(),   // Quốc gia
                movie.getDuration(),
                movie.getDescription(),
                movie.getCast(), // Dàn diễn viên
                movie.getReleaseDate(),
                movie.getImages().stream().map(ImageMapper::toDTO).collect(Collectors.toList()) // Danh sách ảnh
        );
    }

    public static List<MovieDTO> toDTOList(List<Movie> movies) {
        return movies.stream()
                .map(MovieMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static Movie toEntity(MovieDTO movieDTO) {
        Movie movie = new Movie();
        movie.setId(movieDTO.getId());
        movie.setTitle(movieDTO.getTitle());
        movie.setGenre(movieDTO.getGenre());
        movie.setDirector(movieDTO.getDirector());
        movie.setProducer(movieDTO.getProducer());
        movie.setCountry(movieDTO.getCountry());
        movie.setDuration(movieDTO.getDuration());
        movie.setDescription(movieDTO.getDescription());
        movie.setCast(movieDTO.getCast());
        movie.setReleaseDate(movieDTO.getReleaseDate());
        return movie;
    }
}
