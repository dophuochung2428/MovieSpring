package com.example.movie_theater.dtos;

import com.example.movie_theater.entities.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PaymentDTO implements Serializable {
    private String status;
    private String message;
    private String URL;
}

