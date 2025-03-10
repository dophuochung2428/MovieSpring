package com.example.movie_theater.dtos;

import com.example.movie_theater.entities.PaymentStatus;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
    private Long id;
    private Long bookingId; // Chỉ lấy ID của Booking thay vì cả object

    private String paymentMethod;
    private PaymentStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String transactionId;

}
