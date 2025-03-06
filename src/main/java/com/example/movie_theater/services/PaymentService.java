package com.example.movie_theater.services;

import com.example.movie_theater.entities.Payment;

import java.util.Optional;

public interface PaymentService {
    Payment processPayment(Payment payment);
    Optional<Payment> getPaymentByBooking(Long bookingId);
}
