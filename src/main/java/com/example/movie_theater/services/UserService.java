package com.example.movie_theater.services;

import com.example.movie_theater.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    Optional<User> findByUsername(String name);
    User saveUser(User user);
    void deleteUser(Long id);
}
