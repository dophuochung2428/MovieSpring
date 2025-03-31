package com.example.movie_theater.mapper;

import com.example.movie_theater.dtos.ImageDTO;
import com.example.movie_theater.entities.Image;

import java.util.List;
import java.util.stream.Collectors;

public class ImageMapper {
    public static ImageDTO toDTO(Image image) {
        return new ImageDTO(
                image.getId(),
                image.getName(),
                image.getImageUrl()
        );
    }

    public static List<ImageDTO> toDTOList(List<Image> images) {
        return images.stream()
                .map(ImageMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static Image toEntity(ImageDTO imageDTO) {
        Image image = new Image();
        image.setId(imageDTO.getId());
        image.setName(imageDTO.getName());
        image.setImageUrl(imageDTO.getImageUrl());
        return image;
    }
}
