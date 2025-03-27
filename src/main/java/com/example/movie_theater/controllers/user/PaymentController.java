package com.example.movie_theater.controllers.user;


import com.example.movie_theater.dtos.PaymentInfoDTO;
import com.example.movie_theater.config.Config;
import com.example.movie_theater.dtos.PaymentDTO;
import com.example.movie_theater.dtos.TransactionStatusDTO;
import com.example.movie_theater.entities.Booking;
import com.example.movie_theater.entities.Payment;
import com.example.movie_theater.services.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;
import java.io.UnsupportedEncodingException;
import java.util.*;
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


    @PostMapping("/createPaymentByVnPay/{price}/{bookingId}")
    public ResponseEntity<?> createPaymentByVnPay(HttpServletRequest req, @PathVariable long price, @PathVariable long bookingId) throws UnsupportedEncodingException {
        PaymentInfoDTO paymentDTO = paymentService.createPayment(req, price, bookingId);
        return ResponseEntity.ok(paymentDTO);
    }

    //Trang return về bên config

    @GetMapping("/paymentInfo")
    public ResponseEntity<?> transaction(
            @RequestParam("vnp_ResponseCode") String responseCode) {
        TransactionStatusDTO transactionStatusDTO = paymentService
                .processTransaction(responseCode);


        return ResponseEntity.ok(transactionStatusDTO);
    }

    @GetMapping("/payment-callback")
    public ResponseEntity<String> paymentCallback(
            @RequestParam("vnp_ResponseCode") String responseCode,
            @RequestParam("vnp_TxnRef") String txnRef,
            @RequestParam("vnp_OrderInfo") String orderInfo,
            @RequestParam("vnp_PayDate") String createDate) {
        try {
            String bookingId = extractBookingId(orderInfo);
            boolean success = paymentService.handlePaymentCallback(responseCode, txnRef, bookingId, createDate);

            // Nếu thành công, redirect về trang FE hiển thị kết quả
            String redirectUrl = success
                    ? "http://localhost:5173/HoldAndBook?status=success&txnRef=" + txnRef
                    : "http://localhost:5173/HoldAndBook?status=failed";
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header(HttpHeaders.LOCATION, redirectUrl)
                    .build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing payment");
        }
    }


    @GetMapping("/transactionInfo/{txnRef}")
    public ResponseEntity<?> getPaymentInfo(@PathVariable String txnRef) {
        try {
            Map<String, Object> paymentInfo = paymentService.getPaymentInfo(txnRef);
            return ResponseEntity.ok(paymentInfo);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    private String extractBookingId(String orderInfo) {
        String[] parts = orderInfo.split(" - BookingID: ");
        return parts.length > 1 ? parts[1] : null;
    }

}
