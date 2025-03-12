package com.example.movie_theater.services;

import com.example.movie_theater.dtos.AuthResponseDTO;
import com.example.movie_theater.dtos.LoginDTO;
import com.example.movie_theater.dtos.RegisterDTO;

public interface AuthService {
    String register(RegisterDTO registerDTO);
    AuthResponseDTO login(LoginDTO loginDTO);
}
