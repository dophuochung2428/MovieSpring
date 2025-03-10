package com.example.movie_theater.dtos;

import lombok.Data;

@Data
public class RegisterDTO {
    private String username;
    private String password;
    private String mail;
}
