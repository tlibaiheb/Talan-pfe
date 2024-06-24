package com.example.bpiappapi.auth.service;

import com.example.bpiappapi.auth.model.AppUser;
import com.example.bpiappapi.auth.repository.AppUserRepository;
import com.example.bpiappapi.auth.security.token.ConfirmationTokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginServiceTest {

    @Mock
    private AppUserRepository appUserRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private ConfirmationTokenService confirmationTokenService;

    @InjectMocks
    private LoginService loginService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLogin_ValidCredentials() {
        // Given
        String email = "test@example.com";
        String password = "password";
        AppUser user = new AppUser();
        user.setId(1L); // Set a non-null ID
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setEnabled(true);
        when(appUserRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(bCryptPasswordEncoder.matches(password, user.getPassword())).thenReturn(true);

        // When
        String token = loginService.login(email, password);

        // Then
        assertNotNull(token);
        // Add assertions for token validity if necessary
    }


    @Test
    void testLogin_UserNotFound() {
        // Given
        String email = "test@example.com";
        String password = "password";
        when(appUserRepository.findByEmail(email)).thenReturn(Optional.empty());

        // When / Then
        assertThrows(RuntimeException.class, () -> loginService.login(email, password));
    }

    @Test
    void testLogin_UserNotEnabled() {
        // Given
        String email = "test@example.com";
        String password = "password";
        AppUser user = new AppUser();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setEnabled(false);
        when(appUserRepository.findByEmail(email)).thenReturn(Optional.of(user));

        // When / Then
        assertThrows(IllegalStateException.class, () -> loginService.login(email, password));
    }

    @Test
    void testLogin_IncorrectPassword() {
        // Given
        String email = "test@example.com";
        String password = "password";
        AppUser user = new AppUser();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode("differentPassword")); // Different password
        user.setEnabled(true);
        when(appUserRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(bCryptPasswordEncoder.matches(password, user.getPassword())).thenReturn(false);

        // When / Then
        assertThrows(IllegalArgumentException.class, () -> loginService.login(email, password));
    }
}
