package com.example.bpiappapi.auth.service;

import com.example.bpiappapi.auth.model.AppUser;
import com.example.bpiappapi.auth.repository.AppUserRepository;
import com.example.bpiappapi.auth.security.PasswordEncoder;
import com.example.bpiappapi.auth.security.token.ConfirmationToken;
import com.example.bpiappapi.auth.security.token.ConfirmationTokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AppUserServiceTest {

    @Mock
    private AppUserRepository appUserRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private ConfirmationTokenService confirmationTokenService;

    @InjectMocks
    private AppUserService appUserService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Configuration du mock pour retourner une instance valide de BCryptPasswordEncoder
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        when(passwordEncoder.bCryptPasswordEncoder()).thenReturn(encoder);
    }

    @Test
    void testLoadUserByUsername() {
        // Given
        String email = "test@example.com";
        AppUser expectedUser = new AppUser();
        expectedUser.setEmail(email);
        when(appUserRepository.findByEmail(email)).thenReturn(Optional.of(expectedUser));

        // When
        AppUser loadedUser = (AppUser) appUserService.loadUserByUsername(email);

        // Then
        assertEquals(expectedUser.getEmail(), loadedUser.getEmail());
    }

    @Test
    void testDoesUserExist() {
        // Given
        String email = "test@example.com";
        when(appUserRepository.findByEmail(email)).thenReturn(Optional.of(new AppUser()));

        // When
        boolean userExists = appUserService.doesUserExist(email);

        // Then
        assertTrue(userExists);
    }







    @Test
    void testEnableAppUser() {
        // Given
        String email = "test@example.com";
        when(appUserRepository.enableAppUser(email)).thenReturn(1);

        // When
        int result = appUserService.enableAppUser(email);

        // Then
        assertEquals(1, result);
    }




    @Test
    void testFindVerifiedUserByEmail() {
        // Given
        String email = "test@example.com";
        AppUser verifiedUser = new AppUser();
        verifiedUser.setEmail(email);
        verifiedUser.setEnabled(true);
        when(appUserRepository.findByEmailAndEnabled(email, true)).thenReturn(Optional.of(verifiedUser));

        // When
        Optional<AppUser> optionalAppUser = appUserService.findVerifiedUserByEmail(email);

        // Then
        assertTrue(optionalAppUser.isPresent());
        assertEquals(email, optionalAppUser.get().getEmail());
    }
}
