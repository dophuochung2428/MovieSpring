package com.example.movie_theater.services.impl;

import com.example.movie_theater.dtos.UserDTO;
import com.example.movie_theater.entities.User;
import com.example.movie_theater.mapper.UserMapper;
import com.example.movie_theater.repositories.UserRepository;
import com.example.movie_theater.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    @Override
    public UserDTO findByUsername(String name) {

        return userRepository.findByUsername(name)
                .map(UserMapper::toDTO)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }

    @Override
    public void disableUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setEnabled(false);
        userRepository.save(user);
    }
}
