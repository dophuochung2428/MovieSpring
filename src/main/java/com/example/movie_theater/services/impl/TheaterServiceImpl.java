package com.example.movie_theater.services.impl;

import com.example.movie_theater.dtos.HallDTO;
import com.example.movie_theater.dtos.MovieDTO;
import com.example.movie_theater.dtos.TheaterDTO;
import com.example.movie_theater.entities.Movie;
import com.example.movie_theater.entities.Showtime;
import com.example.movie_theater.entities.Theater;
import com.example.movie_theater.exception.DuplicateHallNameException;
import com.example.movie_theater.mapper.TheaterMapper;
import com.example.movie_theater.repositories.MovieRepository;
import com.example.movie_theater.repositories.TheaterRepository;
import com.example.movie_theater.services.HallService;
import com.example.movie_theater.services.TheaterService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TheaterServiceImpl implements TheaterService {
    @Autowired
    private TheaterRepository theaterRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private HallService hallService;

    @Override
    public List<TheaterDTO> getAllTheaters() {

        return TheaterMapper.toDTOList(theaterRepository.findAll());
    }

    @Override
    public List<TheaterDTO> findByLocation(String location) {
        return TheaterMapper.toDTOList(theaterRepository.findByLocationContainingIgnoreCase(location));
    }

    @Override
    public List<MovieDTO> getMovieInTheater(Long id) {
        Theater theater = theaterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
        return theater.getHalls().stream()
                .flatMap(hall -> hall.getShowtimes().stream())
                .map(Showtime::getMovie)
                .distinct()
                .map(movie -> new MovieDTO(
                        movie.getId(),
                        movie.getTitle(),
                        movie.getGenre(),
                        movie.getDirector(),
                        movie.getDuration(),
                        movie.getDescription(),
                        movie.getReleaseDate()
                        ))
                .collect(Collectors.toList());
    }

    @Override
    public Theater getTheaterById(Long id) {
        return theaterRepository.findById(id).orElse(null);
    }

    @Override
    public TheaterDTO saveTheater(TheaterDTO theaterDTO) {
        Theater theater = theaterRepository.save(TheaterMapper.toEntity(theaterDTO));
        return TheaterMapper.toDTO(theater);
    }

    @Override
    public TheaterDTO updateTheater(Long id, TheaterDTO theaterDTO) {
        Theater theater = theaterRepository.findById(id).orElseThrow();
        theater.setLocation(theaterDTO.getLocation());
        theater.setName(theaterDTO.getName());
        theater.setTotalHalls(theaterDTO.getTotalHalls());
        return TheaterMapper.toDTO(theaterRepository.save(theater));
    }

    @Override
    public void deleteTheater(Long id) {
        theaterRepository.deleteById(id);
    }

    @Override
    public void checkName(Long id, String name) {
        Theater theater = theaterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rạp không tồn tại với id: " + id));
        boolean exists = theater.getHalls().stream()
                .anyMatch(hall -> hall.getName().equalsIgnoreCase(name));
        if(exists){
            throw new DuplicateHallNameException("Tên phòng chiếu '" + name + "' đã tồn tại trong rạp: " + theater.getName());
        }
    }

    @Override
    public TheaterDTO createTheaterWithHall(TheaterDTO theaterDTO){
        Theater theater = new Theater();
        theater.setLocation(theaterDTO.getLocation());
        theater.setName(theaterDTO.getName());
        theater.setTotalHalls(theaterDTO.getTotalHalls());

        Theater savedTheater = theaterRepository.save(theater);
        if(theaterDTO.getTotalHalls()>0){
            hallService.createHallForTheater(savedTheater);
        }
        return TheaterMapper.toDTO(savedTheater);
    }

    @Override
    public List<HallDTO> getHallsByTheater(Long theaterId) {
        Theater theater = theaterRepository.findById(theaterId).orElseThrow();
        return theater.getHalls().stream()
                .map(hall -> new HallDTO(
                        hall.getId(),
                        hall.getName(),
                        hall.getTotalSeats(),
                        hall.getTheater().getId(),
                        hall.getTheater().getName()
                ))
                .collect(Collectors.toList());
    }
}
