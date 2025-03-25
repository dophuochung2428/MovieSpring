package com.example.movie_theater.services.impl;

import com.example.movie_theater.dtos.UserDTO;
import com.example.movie_theater.entities.User;
import com.example.movie_theater.mapper.UserMapper;
import com.example.movie_theater.repositories.UserRepository;
import com.example.movie_theater.security.JWTGenerator;
import com.example.movie_theater.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JWTGenerator jwtGenerator;

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

    @Override
    public UserDTO getUserFromToken(String token) {
        String username = jwtGenerator.getUsernameFromJWT(token);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return UserMapper.toDTO(user);
    }

    @Override
    public UserDTO findByEmail(String email) {
        return userRepository.findByEmail(email).map(UserMapper::toDTO)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }
}
