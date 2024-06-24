package com.example.bpiappapi.auth.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

public class EmailSenderPassword implements EmailSender{
    private final JavaMailSender javaMailSender;

    public EmailSenderPassword(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(String to, String content) {
        SimpleMailMessage message = new SimpleMailMessage(); // Utilisation de SimpleMailMessage au lieu de EmailSenderPassword
        message.setTo(to);
        message.setText(content);
        javaMailSender.send(message);
    }

}
