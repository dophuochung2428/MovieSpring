package com.example.movie_theater.services;

<<<<<<< Updated upstream
=======
import com.example.movie_theater.dtos.PaymentDTO;
import com.example.movie_theater.dtos.PaymentInfoDTO;
import com.example.movie_theater.dtos.TransactionStatusDTO;
>>>>>>> Stashed changes
import com.example.movie_theater.entities.Payment;

import java.util.Optional;

public interface PaymentService {
    Payment processPayment(Payment payment);
    Optional<Payment> getPaymentByBooking(Long bookingId);
<<<<<<< Updated upstream
=======

    PaymentInfoDTO createPayment(HttpServletRequest req, long price, long booingId) throws UnsupportedEncodingException;
    PaymentDTO savePayment(PaymentDTO paymentDTO);
    String buildQuery(Map<String, String> vnp_Params) throws UnsupportedEncodingException;

    TransactionStatusDTO processTransaction(String responseCode);

    boolean handlePaymentCallback(String responseCode, String txnRef, String bookingId, String createDate);
>>>>>>> Stashed changes
}
