package com.example.movie_theater.services;

import com.example.movie_theater.dtos.PaymentDTO;
import com.example.movie_theater.dtos.TransactionStatusDTO;
import com.example.movie_theater.entities.Payment;
import jakarta.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Optional;

public interface PaymentService {
    Payment processPayment(Payment payment);
    Optional<Payment> getPaymentByBooking(Long bookingId);

    PaymentDTO createPayment(HttpServletRequest req, long price) throws UnsupportedEncodingException;

    String buildQuery(Map<String, String> vnp_Params) throws UnsupportedEncodingException;

    TransactionStatusDTO processTransaction(String responseCode);
}
