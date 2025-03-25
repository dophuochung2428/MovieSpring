package com.example.movie_theater.services;

import org.springframework.scheduling.annotation.Scheduled;

public interface ForgotPasswordService {
    // Chạy mỗi 1 phút để xóa OTP hết hạn
    @Scheduled(fixedRate = 60 * 1000) // 60 giây
    void deleteExpiredOtpsPer60();

    void deleteExpiredOtps();
}
