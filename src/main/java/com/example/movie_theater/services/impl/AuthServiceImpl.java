package com.example.movie_theater.services.impl;

import com.example.movie_theater.dtos.AuthResponseDTO;
import com.example.movie_theater.dtos.LoginDTO;
import com.example.movie_theater.dtos.RegisterDTO;
import com.example.movie_theater.entities.GenderStatus;
import com.example.movie_theater.entities.Role;
import com.example.movie_theater.entities.User;
import com.example.movie_theater.repositories.RoleRepository;
import com.example.movie_theater.repositories.UserRepository;
import com.example.movie_theater.security.JWTGenerator;
import com.example.movie_theater.services.AuthService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTGenerator jwtGenerator;

    @Override
    @Transactional
    public String register(RegisterDTO registerDTO) {
        if(userRepository.existsByUsername(registerDTO.getUsername())){
            throw new RuntimeException("Username is taken!");
        }

        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setFullName(registerDTO.getFullName());
        user.setGender(GenderStatus.valueOf(registerDTO.getGender().toUpperCase()));
        user.setDateOfBirth(registerDTO.getDateOfBirth());
        user.setCreatedAt(LocalDateTime.now());
        user.setEmail(registerDTO.getEmail());

        Role role = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRole(role);

        userRepository.save(user);
        return "User registered success";
    }

    @Override
    public AuthResponseDTO login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),
                        loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generatorToken(authentication);
        return new AuthResponseDTO(token);
    }
}
