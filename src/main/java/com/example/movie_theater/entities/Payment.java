package com.example.movie_theater.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "booking_id", nullable = false, unique = true) // 🔥 Một Booking chỉ có một Payment
    private Booking booking;

    @Column(nullable = false)
    private double amount; // 🔥 Số tiền thanh toán (nên lấy từ `Booking.price`)

    @Column(nullable = false)
    private String paymentMethod; // 🔥 (CREDIT_CARD, MOMO, CASH, PAYPAL...)

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatus status = PaymentStatus.PENDING; // 🔥 Trạng thái thanh toán

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt; // 🔥 Thời gian tạo Payment

    private LocalDateTime updatedAt; // 🔥 Thời gian cập nhật Payment

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
