package com.example.movie_theater.repositories;

import com.example.movie_theater.entities.ForgotPassword;
import com.example.movie_theater.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword, Integer> {
    @Query("select fp from ForgotPassword fp where fp.otp = ?1 and fp.user =?2")
    Optional<ForgotPassword> findByOtpAndUser(Integer otp, User user);

    List<ForgotPassword> findAllByExpirationTimeBefore(Date currentDate);
    void deleteByExpirationTimeBefore(Date now);
}
