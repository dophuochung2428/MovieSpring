package com.example.movie_theater.services.impl;


import com.example.movie_theater.config.Config;
import com.example.movie_theater.dtos.PaymentDTO;
import com.example.movie_theater.dtos.PaymentInfoDTO;
import com.example.movie_theater.dtos.TransactionStatusDTO;
import com.example.movie_theater.entities.Booking;
import com.example.movie_theater.entities.BookingStatus;
import com.example.movie_theater.entities.Payment;
import com.example.movie_theater.entities.PaymentStatus;
import com.example.movie_theater.mapper.PaymentMapper;
import com.example.movie_theater.repositories.BookingRepository;

import com.example.movie_theater.repositories.PaymentRepository;
import com.example.movie_theater.services.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public Payment processPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Optional<Payment> getPaymentByBooking(Long bookingId) {
        return paymentRepository.findByBookingId(bookingId);
    }


    @Override
    public PaymentInfoDTO createPayment(HttpServletRequest req, long price, long bookingId) throws UnsupportedEncodingException {

        long amount = price * 100;
        String vnp_TxnRef = Config.getRandomNumber(8);
        String vnp_IpAddr = Config.getIpAddress(req);
        String vnp_TmnCode = Config.vnp_TmnCode;
        String orderType = Optional.ofNullable(req.getParameter("ordertype")).orElse("other");

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", Config.vnp_Version);
        vnp_Params.put("vnp_Command", Config.vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_BankCode", "NCB");
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);
        vnp_Params.put("vnp_OrderType", orderType);
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);

        vnp_Params.put("vnp_OrderInfo", "Thanh toán đơn hàng: " + vnp_TxnRef + " - BookingID: " + bookingId);
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl", "http://localhost:8080/api/payments/payment-callback");

        // Set thời gian
        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        vnp_Params.put("vnp_CreateDate", formatter.format(cld.getTime()));

        cld.add(Calendar.MINUTE, 15);
        vnp_Params.put("vnp_ExpireDate", formatter.format(cld.getTime()));

        // Tạo query và hash
        String queryUrl = buildQuery(vnp_Params);
        String vnp_SecureHash = Config.hmacSHA512(Config.secretKey, queryUrl);
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;

        String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;


        PaymentInfoDTO paymentDTO = new PaymentInfoDTO();

        paymentDTO.setStatus("Ok");
        paymentDTO.setMessage("Successfully");
        paymentDTO.setURL(paymentUrl);

        return paymentDTO;
    }

    @Override

    public PaymentDTO savePayment(PaymentDTO paymentDTO) {
        Booking booking = bookingRepository.findById(paymentDTO.getBookingId())
                .orElseThrow();
        Payment payment = PaymentMapper.toEntity(paymentDTO, booking);
        return PaymentMapper.toDTO(paymentRepository.save(payment));
    }

    @Override

    public String buildQuery(Map<String, String> vnp_Params) throws UnsupportedEncodingException {
        List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
        Collections.sort(fieldNames);

        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();

        for (String fieldName : fieldNames) {
            String fieldValue = vnp_Params.get(fieldName);
            if (fieldValue != null && !fieldValue.isEmpty()) {
                hashData.append(fieldName).append('=').append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()))
                        .append('=')
                        .append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()))
                        .append('&');
            }
        }

        return query.substring(0, query.length() - 1); // Xóa dấu `&` cuối cùng
    }

    @Override
    public TransactionStatusDTO processTransaction(String responseCode) {
        TransactionStatusDTO transactionStatusDTO = new TransactionStatusDTO();
        if ("00".equals(responseCode)) {
            transactionStatusDTO.setStatus("Ok");
            transactionStatusDTO.setMessage("Successfully");
        } else {
            transactionStatusDTO.setStatus("No");
            transactionStatusDTO.setMessage("Failed");
        }
        return transactionStatusDTO;
    }

    @Override
    public boolean handlePaymentCallback(String responseCode, String txnRef, String bookingId, String createDate) {
        if (!"00".equals(responseCode)) {
            return false; // Thanh toán thất bại
        }
        Long longId = Long.parseLong(bookingId);
        Booking bookingFound = bookingRepository.findById((longId))
                .orElseThrow();
        //Lưu payment

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime createdAt = LocalDateTime.parse(createDate, formatter);

        Payment payment = new Payment();
        payment.setCreatedAt(createdAt);
        payment.setPaymentMethod("online");
        payment.setStatus(PaymentStatus.COMPLETED);
        payment.setUpdatedAt(createdAt);
        payment.setBooking(bookingFound);
        payment.setTransactionId(txnRef);

        paymentRepository.save(payment);
        // Cập nhật trạng thái booking thành CONFIRMED
        Long id = Long.parseLong(bookingId);
        Optional<Booking> bookingOpt = bookingRepository.findById(id);
        if (bookingOpt.isPresent()) {
            Booking booking = bookingOpt.get();
            booking.setBookingStatus(BookingStatus.CONFIRMED);
            bookingRepository.save(booking);
            return true;
        }

        return false;
    }


}
