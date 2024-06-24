package com.example.bpiappapi.auth.security.token;

import com.example.bpiappapi.auth.model.AppUser;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class ConfirmationTokenTest {

    @Test
    void isExpired_NotExpired_ReturnsFalse() {
        // Arrange
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiresAt = now.plusHours(1); // Expire dans une heure
        ConfirmationToken token = new ConfirmationToken("token", now, expiresAt, new AppUser());

        // Act
        boolean isExpired = token.isExpired();

        // Assert
        assertFalse(isExpired);
    }

    @Test
    void isExpired_AlreadyExpired_ReturnsTrue() {
        // Arrange
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiresAt = now.minusHours(1); // Expire depuis une heure
        ConfirmationToken token = new ConfirmationToken("token", now, expiresAt, new AppUser());

        // Act
        boolean isExpired = token.isExpired();

        // Assert
        assertTrue(isExpired);
    }

    @Test
    void testSettersAndGetters() {
        // Arrange
        ConfirmationToken token = new ConfirmationToken();
        AppUser user = new AppUser();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiresAt = now.plusHours(1);

        // Act
        token.setId(1L);
        token.setToken("testToken");
        token.setCreatedAt(now);
        token.setExpiresAt(expiresAt);
        token.setConfirmedAt(now.plusMinutes(30));
        token.setAppUser(user);

        // Assert
        assertEquals(1L, token.getId());
        assertEquals("testToken", token.getToken());
        assertEquals(now, token.getCreatedAt());
        assertEquals(expiresAt, token.getExpiresAt());
        assertEquals(now.plusMinutes(30), token.getConfirmedAt());
        assertEquals(user, token.getAppUser());
    }
}
