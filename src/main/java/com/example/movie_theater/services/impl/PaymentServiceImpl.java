package com.example.movie_theater.services.impl;

import com.example.movie_theater.entities.Payment;
import com.example.movie_theater.repositories.PaymentRepository;
import com.example.movie_theater.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment processPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Optional<Payment> getPaymentByBooking(Long bookingId) {
        return paymentRepository.findByBookingId(bookingId);
    }
}
