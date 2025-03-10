package com.example.movie_theater.mapper;

import com.example.movie_theater.dtos.PaymentDTO;
import com.example.movie_theater.entities.Booking;
import com.example.movie_theater.entities.Payment;

public class PaymentMapper {

    public static PaymentDTO toDTO(Payment payment) {
        return new PaymentDTO(
                payment.getId(),
                payment.getBooking().getId(),
                payment.getPaymentMethod(),
                payment.getStatus(),
                payment.getCreatedAt(),
                payment.getUpdatedAt(),
                payment.getTransactionId()

        );
    }
    public static Payment toEntity(PaymentDTO dto, Booking booking) {
        return new Payment(
                dto.getId(),
                booking,
                dto.getPaymentMethod(),
                dto.getStatus(),
                dto.getCreatedAt(),
                dto.getUpdatedAt(),
                dto.getTransactionId()
        );
    }
 
}

