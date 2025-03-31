package com.example.movie_theater.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.movie_theater.dtos.ImageDTO;
import com.example.movie_theater.dtos.MovieDTO;
import com.example.movie_theater.entities.Image;
import com.example.movie_theater.entities.Movie;
import com.example.movie_theater.mapper.ImageMapper;
import com.example.movie_theater.mapper.MovieMapper;
import com.example.movie_theater.repositories.ImageRepository;
import com.example.movie_theater.services.CloudinaryService;
import com.example.movie_theater.services.ImageService;
import com.example.movie_theater.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private ImageService imageService;


    @Transactional
    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file,
                                         @RequestParam("movieId") Long movieId,
                                         @RequestParam("name") String name) {
        try {
            String imageUrl = cloudinaryService.uploadImage(file);

            MovieDTO movieDTO = movieService.getMovieById(movieId);
            if (movieDTO == null) {
                return ResponseEntity.badRequest().body("Movie not found with ID: " + movieId);
            }
            Movie movie = MovieMapper.toEntity(movieDTO);

            Image image = new Image();
            image.setName(name);
            image.setImageUrl(imageUrl);
            image.setMovie(movie);

            imageService.uploadImage(image);

            return ResponseEntity.ok(ImageMapper.toDTO(image));
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error uploading image: " + e.getMessage());
        }
    }


    @PostMapping("/save")
    public ResponseEntity<?> saveImage(@RequestBody Image image) {
        imageRepository.save(image);
        return ResponseEntity.ok("Image saved!");
    }

    @GetMapping
    public ResponseEntity<List<Image>> getAllImages() {
        return ResponseEntity.ok(imageRepository.findAll());
    }


}

