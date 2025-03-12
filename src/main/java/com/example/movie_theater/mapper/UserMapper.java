package com.example.movie_theater.mapper;

import com.example.movie_theater.dtos.UserDTO;
import com.example.movie_theater.entities.GenderStatus;
import com.example.movie_theater.entities.User;

public class UserMapper {
    public static UserDTO toDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole().getName(),
                user.isEnabled(),
                user.getCreatedAt(),
                user.getFullName(),
                user.getGender().name(),
                user.getDateOfBirth()
        );
    }
    public static User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setEnabled(userDTO.isEnabled());
        user.setCreatedAt(userDTO.getCreatedAt());
        user.setFullName(userDTO.getFullName());
        user.setGender(GenderStatus.valueOf(userDTO.getGender()));
        user.setDateOfBirth(userDTO.getDateOfBirth());
        return user;
    }
}

