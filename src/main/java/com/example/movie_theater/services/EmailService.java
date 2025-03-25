package com.example.movie_theater.services;

import com.example.movie_theater.dtos.MailBody;

public interface EmailService {
    void sendSimpleMessage(MailBody mailBody);
}
