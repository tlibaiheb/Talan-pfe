package com.example.bpiappapi.auth.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ResetPasswordRequestTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ResetPasswordRequest#setNewPassword(String)}
     *   <li>{@link ResetPasswordRequest#setToken(String)}
     *   <li>{@link ResetPasswordRequest#getNewPassword()}
     *   <li>{@link ResetPasswordRequest#getToken()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest();

        // Act
        resetPasswordRequest.setNewPassword("iloveyou");
        resetPasswordRequest.setToken("ABC123");
        String actualNewPassword = resetPasswordRequest.getNewPassword();

        // Assert that nothing has changed
        assertEquals("ABC123", resetPasswordRequest.getToken());
        assertEquals("iloveyou", actualNewPassword);
    }
}
