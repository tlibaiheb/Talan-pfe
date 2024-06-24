package com.example.bpiappapi.auth.email;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class EmailSenderPasswordTest {

    @Mock
    private JavaMailSender javaMailSender;

    private EmailSenderPassword emailSenderPassword;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        emailSenderPassword = new EmailSenderPassword(javaMailSender);
    }

    @Test
    public void testSendEmail() {
        String to = "recipient@example.com";
        String content = "Test email content";

        emailSenderPassword.sendEmail(to, content);

        SimpleMailMessage expectedMessage = new SimpleMailMessage();
        expectedMessage.setTo(to);
        expectedMessage.setText(content);

        verify(javaMailSender).send(expectedMessage);
    }
}
