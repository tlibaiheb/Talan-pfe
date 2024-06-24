package com.example.bpiappapi.auth.controller;

import com.example.bpiappapi.auth.model.MissingPasswordRequest;
import com.example.bpiappapi.auth.model.ResetPasswordRequest;
import com.example.bpiappapi.auth.service.MissingPasswordService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MissingPasswordControllerTest {

    @Mock
    private MissingPasswordService missingPasswordService;

    @InjectMocks
    private MissingPasswordController missingPasswordController;

    @Test
    void forgotPassword_ValidEmail_EmailSentSuccessfully() {
        // Arrange
        MissingPasswordRequest request = new MissingPasswordRequest();
        request.setEmail("test@example.com");

        // Act
        ResponseEntity<String> response = missingPasswordController.forgotPassword(request);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Un email contenant votre adresse e-mail a été envoyé.", response.getBody());
        verify(missingPasswordService, times(1)).sendEmailToUser("test@example.com");
    }

    @Test
    void forgotPassword_InvalidEmail_ReturnsBadRequest() {
        // Arrange
        MissingPasswordRequest request = new MissingPasswordRequest();
        request.setEmail("invalidemail");

        // Act
        ResponseEntity<String> response = missingPasswordController.forgotPassword(request);

        // Assert
        // Add more assertions if needed
    }

    // Add more test cases for other scenarios related to forgotPassword method

    @Test
    void resetPassword_ValidRequest_PasswordResetSuccessfully() {
        // Arrange
        ResetPasswordRequest request = new ResetPasswordRequest();
        request.setToken("validToken");
        request.setNewPassword("newPassword");

        // Act
        ResponseEntity<Map<String, String>> response = missingPasswordController.resetPassword(request);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Le mot de passe a été réinitialisé avec succès.", response.getBody().get("message"));
        verify(missingPasswordService, times(1)).resetPassword("validToken", "newPassword");
    }

    @Test
    void resetPassword_InvalidToken_ReturnsBadRequest() {
        // Arrange
        ResetPasswordRequest request = new ResetPasswordRequest();
        request.setToken("invalidToken");
        request.setNewPassword("newPassword");

        // Act
        ResponseEntity<Map<String, String>> response = missingPasswordController.resetPassword(request);

        // Assert
        // Add more assertions if needed
    }

    // Add more test cases for other scenarios related to resetPassword method
}
