package com.example.movie_theater.services;

import com.example.movie_theater.dtos.ImageDTO;
import com.example.movie_theater.entities.Image;

public interface ImageService {
    ImageDTO uploadImage(Image image);
}
