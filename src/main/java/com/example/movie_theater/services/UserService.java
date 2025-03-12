package com.example.movie_theater.services;

import com.example.movie_theater.dtos.UserDTO;
import com.example.movie_theater.entities.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    UserDTO findByUsername(String name);
    void disableUser(Long id);
}
