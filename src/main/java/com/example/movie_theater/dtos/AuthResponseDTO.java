package com.example.movie_theater.dtos;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class AuthResponseDTO {

    private String accessToken;

    private String tokenType = "Bearer ";

    public AuthResponseDTO(String accessToken){
        this.accessToken = accessToken;
    }

}
