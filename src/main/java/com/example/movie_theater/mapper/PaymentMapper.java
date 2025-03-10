package com.example.movie_theater.mapper;

import com.example.movie_theater.dtos.PaymentDTO;
import com.example.movie_theater.entities.Booking;
import com.example.movie_theater.entities.Payment;

public class PaymentMapper {
    public static PaymentDTO toDTO(Payment payment) {
        return new PaymentDTO(
                payment.getId(),
                payment.getBooking().getId(),
<<<<<<< Updated upstream
                payment.getAmount(),
                payment.getPaymentMethod(),
                payment.getStatus(),
                payment.getCreatedAt(),
                payment.getUpdatedAt()
=======
                payment.getPaymentMethod(),
                payment.getStatus(),
                payment.getCreatedAt(),
                payment.getUpdatedAt(),
                payment.getTransactionId()
>>>>>>> Stashed changes
        );
    }
    public static Payment toEntity(PaymentDTO dto, Booking booking) {
        return new Payment(
                dto.getId(),
                booking,
<<<<<<< Updated upstream
                dto.getAmount(),
                dto.getPaymentMethod(),
                dto.getStatus(),
                dto.getCreatedAt(),
                dto.getUpdatedAt()
=======
                dto.getPaymentMethod(),
                dto.getStatus(),
                dto.getCreatedAt(),
                dto.getUpdatedAt(),
                dto.getTransactionId()
>>>>>>> Stashed changes
        );
    }
}

