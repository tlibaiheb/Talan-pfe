package com.example.bpiappapi.auth.controller;

import com.example.bpiappapi.auth.model.AppUser;
import com.example.bpiappapi.auth.service.AppUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private AppUserService appUserService;

    @InjectMocks
    private UserController userController;

    @Test
    void getUserFromToken_ValidToken_ReturnsUser() {
        // Arrange
        String validToken = "validToken";
        AppUser expectedUser = new AppUser();
        when(appUserService.getUserFromToken(validToken)).thenReturn(expectedUser);

        // Act
        AppUser actualUser = userController.getUserFromToken("Bearer " + validToken);

        // Assert
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void getUserFromToken_InvalidToken_ThrowsException() {
        // Arrange
        String invalidToken = "invalidToken";
        when(appUserService.getUserFromToken(anyString())).thenThrow(IllegalArgumentException.class);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            userController.getUserFromToken("Bearer " + invalidToken);
        });
    }

    @Test
    void getUserFromToken_NoAuthorizationHeader_ThrowsException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            userController.getUserFromToken(null);
        });
    }

    @Test
    void getUserFromToken_EmptyAuthorizationHeader_ThrowsException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            userController.getUserFromToken("");
        });
    }

    @Test
    void getUserFromToken_InvalidAuthorizationHeaderFormat_ThrowsException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            userController.getUserFromToken("InvalidFormat");
        });
    }
}
