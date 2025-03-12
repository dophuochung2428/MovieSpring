package com.example.movie_theater.dtos;

import com.example.movie_theater.entities.GenderStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterDTO {
    private String username;
    private String password;
    private String fullName;
    private String gender;
    private LocalDate dateOfBirth;
    private String email;
}
