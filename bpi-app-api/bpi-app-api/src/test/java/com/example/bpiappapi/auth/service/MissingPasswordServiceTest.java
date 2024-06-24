package com.example.bpiappapi.auth.service;

import com.example.bpiappapi.auth.email.EmailSender;
import com.example.bpiappapi.auth.model.AppUser;
import com.example.bpiappapi.auth.repository.AppUserRepository;
import com.example.bpiappapi.auth.security.EmailValidator;
import com.example.bpiappapi.auth.security.PasswordEncoder;
import com.example.bpiappapi.auth.security.token.ConfirmationToken;
import com.example.bpiappapi.auth.security.token.ConfirmationTokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

 class MissingPasswordServiceTest {

    @Mock
    private EmailSender emailSender;

    @Mock
    private EmailValidator emailValidator;

    @Mock
    private AppUserService appUserService;

    @Mock
    private ConfirmationTokenService confirmationTokenService;

    @Mock
    private AppUserRepository appUserRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private MissingPasswordService missingPasswordService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
     void testSendEmailToUser_ValidEmail() {
        String userEmail = "test@example.com";
        AppUser user = new AppUser();
        user.setFirstName("John");
        when(emailValidator.test(userEmail)).thenReturn(true);
        when(appUserService.findVerifiedUserByEmail(userEmail)).thenReturn(Optional.of(user));
        missingPasswordService.sendEmailToUser(userEmail);
        verify(emailSender, times(1)).sendEmail(anyString(), anyString());
    }

    @Test
    void testSendEmailToUser_InvalidEmail() {
        String userEmail = "invalidemail";
        when(emailValidator.test(userEmail)).thenReturn(false);
        assertThrows(IllegalArgumentException.class, () -> missingPasswordService.sendEmailToUser(userEmail));
    }

    @Test
     void testSendEmailToUser_UserNotFound() {
        String userEmail = "test@example.com";
        when(emailValidator.test(userEmail)).thenReturn(true);
        when(appUserService.findVerifiedUserByEmail(userEmail)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> missingPasswordService.sendEmailToUser(userEmail));
    }



    @Test
     void testResetPassword_ExpiredToken() {
        String token = "expiredToken";
        String newPassword = "newPassword";
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now().minusHours(2), LocalDateTime.now().minusHours(1), new AppUser());
        when(confirmationTokenService.getToken(token)).thenReturn(Optional.of(confirmationToken));
        assertThrows(IllegalArgumentException.class, () -> missingPasswordService.resetPassword(token, newPassword));
        verify(appUserRepository, never()).save(any());
    }


}
