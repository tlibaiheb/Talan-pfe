package com.example.bpiappapi.auth.email;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EmailService.class})
@ExtendWith(SpringExtension.class)
class EmailServiceTest {
    @Autowired
    private EmailService emailService;

    @MockBean
    private JavaMailSender javaMailSender;

    /**
     * Method under test: {@link EmailService#sendEmail(String, String)}
     */
    @Test
    void testSendEmail() throws MailException {
        // Arrange
        doNothing().when(javaMailSender).send(Mockito.<MimeMessage>any());
        when(javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));

        // Act
        emailService.sendEmail("alice.liddell@example.org", "jane.doe@example.org");

        // Assert
        verify(javaMailSender).createMimeMessage();
        verify(javaMailSender).send(isA(MimeMessage.class));
    }

    /**
     * Method under test: {@link EmailService#sendEmail(String, String)}
     */
    @Test
    void testSendEmail2() throws MailException {
        // Arrange
        doThrow(new IllegalArgumentException("utf-8")).when(javaMailSender).send(Mockito.<MimeMessage>any());
        when(javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> emailService.sendEmail("alice.liddell@example.org", "jane.doe@example.org"));
        verify(javaMailSender).createMimeMessage();
        verify(javaMailSender).send(isA(MimeMessage.class));
    }

    /**
     * Method under test: {@link EmailService#sendEmail(String, String)}
     */
    @Test
    void testSendEmail3() {
        // Arrange
        when(javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> emailService.sendEmail("Confirm your email", "jane.doe@example.org"));
        verify(javaMailSender).createMimeMessage();
    }

    /**
     * Method under test: {@link EmailService#sendEmail(String, String)}
     */
    @Test
    void testSendEmail4() {
        // Arrange
        when(javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> emailService.sendEmail("", "jane.doe@example.org"));
        verify(javaMailSender).createMimeMessage();
    }
}
