package com.example.movie_theater.services.impl;

import com.example.movie_theater.dtos.ImageDTO;
import com.example.movie_theater.entities.Image;
import com.example.movie_theater.mapper.ImageMapper;
import com.example.movie_theater.repositories.ImageRepository;
import com.example.movie_theater.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public ImageDTO uploadImage(Image image) {
        Image savedImage = imageRepository.save(image);
        return ImageMapper.toDTO(savedImage);
    }
}
