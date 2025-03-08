package com.example.movie_theater.entities;

public enum BookingStatus {
    PENDING, // Chờ thanh toán
    CONFIRMED, // Đã thanh toán
    CANCELLED, // Đã hủy
    WAITING_FOR_PAYMENT // Chờ thanh toán
}
