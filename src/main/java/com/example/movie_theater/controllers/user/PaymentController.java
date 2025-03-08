package com.example.movie_theater.controllers.user;

import com.example.movie_theater.config.Config;
import com.example.movie_theater.dtos.PaymentDTO;
import com.example.movie_theater.dtos.TransactionStatusDTO;
import com.example.movie_theater.entities.Payment;
import com.example.movie_theater.services.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/process")
    public ResponseEntity<Payment> processPayment(@RequestBody Payment payment) {
        return ResponseEntity.ok(paymentService.processPayment(payment));
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<Optional<Payment>> getPaymentByBooking(@PathVariable Long bookingId) {
        return ResponseEntity.ok(paymentService.getPaymentByBooking(bookingId));
    }

    @PostMapping("/createPayment/{price}")
    public ResponseEntity<?> createPayment(HttpServletRequest req, @PathVariable long price) throws UnsupportedEncodingException {
        PaymentDTO paymentDTO = paymentService.createPayment(req, price);
        return ResponseEntity.ok(paymentDTO);
    }

    //trang return về bên config
    @GetMapping("/paymentInfo")
    public ResponseEntity<?> transaction(
            @RequestParam("vnp_ResponseCode") String responseCode) {
        TransactionStatusDTO transactionStatusDTO = paymentService.processTransaction(responseCode);
        return ResponseEntity.ok(transactionStatusDTO);
    }

}
