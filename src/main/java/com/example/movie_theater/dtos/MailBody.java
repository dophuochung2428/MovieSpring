package com.example.movie_theater.dtos;

import lombok.Builder;

@Builder
public record MailBody(String to, String subject, String text) {
}
