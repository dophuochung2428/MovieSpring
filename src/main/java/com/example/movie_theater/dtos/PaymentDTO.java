package com.example.movie_theater.dtos;

import com.example.movie_theater.entities.PaymentStatus;
import lombok.*;

import java.time.LocalDateTime;

<<<<<<< Updated upstream
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PaymentDTO {
    private Long id;
    private Long bookingId;
    private double amount;
=======
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
    private Long id;
    private Long bookingId; // Chỉ lấy ID của Booking thay vì cả object
>>>>>>> Stashed changes
    private String paymentMethod;
    private PaymentStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
<<<<<<< Updated upstream
=======
    private String transactionId;
>>>>>>> Stashed changes
}
