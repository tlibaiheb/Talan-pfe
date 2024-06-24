package com.example.bpiappapi.auth.security.token;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ConfirmationTokenServiceTest {

    @Mock
    private ConfirmationTokenRepository confirmationTokenRepository;

    @InjectMocks
    private ConfirmationTokenService confirmationTokenService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveConfirmationToken() {
        ConfirmationToken token = new ConfirmationToken();
        confirmationTokenService.saveConfirmationToken(token);
        verify(confirmationTokenRepository, times(1)).save(token);
    }

    @Test
    void testGetToken() {
        String tokenString = "testToken";
        ConfirmationToken token = new ConfirmationToken();
        when(confirmationTokenRepository.findByToken(tokenString)).thenReturn(Optional.of(token));

        Optional<ConfirmationToken> returnedToken = confirmationTokenService.getToken(tokenString);

        assertTrue(returnedToken.isPresent());
        assertEquals(token, returnedToken.get());
        verify(confirmationTokenRepository, times(1)).findByToken(tokenString);
    }

    @Test
    void testSetConfirmedAt() {
        String tokenString = "testToken";
        LocalDateTime now = LocalDateTime.now();
        when(confirmationTokenRepository.updateConfirmedAt(anyString(), any(LocalDateTime.class))).thenReturn(1);

        int updateCount = confirmationTokenService.setConfirmedAt(tokenString);

        assertEquals(1, updateCount);
        verify(confirmationTokenRepository, times(1)).updateConfirmedAt(eq(tokenString), any(LocalDateTime.class));
    }
}
