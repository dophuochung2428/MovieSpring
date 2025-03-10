package com.example.movie_theater.dtos;

import lombok.*;

import java.io.Serializable;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PaymentInfoDTO implements Serializable {
    private String status;
    private String message;
    private String URL;
}

