package com.example.movie_theater.services.impl;

import com.example.movie_theater.entities.ForgotPassword;
import com.example.movie_theater.repositories.ForgotPasswordRepository;
import com.example.movie_theater.services.ForgotPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

    @Autowired
    private ForgotPasswordRepository forgotPasswordRepo;

    // Chạy mỗi 1 phút để xóa OTP hết hạn
    @Override
    @Scheduled(fixedRate = 60 * 1000) // 60 giây
    @Transactional
    public void deleteExpiredOtpsPer60() {
        forgotPasswordRepo.deleteByExpirationTimeBefore(Date.from(Instant.now()));
        System.out.println("Deleted expired OTPs at " + new Date());
    }

    @Override
    public void deleteExpiredOtps() {
        Instant oneMinuteAgo = Instant.now().minusSeconds(70);
        List<ForgotPassword> expiredOtps = forgotPasswordRepo.findAllByExpirationTimeBefore(Date.from(oneMinuteAgo));
        for (ForgotPassword fp : expiredOtps) {
            forgotPasswordRepo.delete(fp);
        }
    }
}
