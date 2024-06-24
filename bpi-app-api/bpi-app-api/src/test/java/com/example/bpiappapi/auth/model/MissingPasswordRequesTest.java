package com.example.bpiappapi.auth.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MissingPasswordRequesTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link MissingPasswordRequest#MissingPasswordRequest()}
     *   <li>{@link MissingPasswordRequest#setEmail(String)}
     *   <li>{@link MissingPasswordRequest#getEmail()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        MissingPasswordRequest actualMissingPasswordRequest = new MissingPasswordRequest();
        actualMissingPasswordRequest.setEmail("jane.doe@example.org");

        // Assert that nothing has changed
        assertEquals("jane.doe@example.org", actualMissingPasswordRequest.getEmail());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link MissingPasswordRequest#MissingPasswordRequest(String)}
     *   <li>{@link MissingPasswordRequest#setEmail(String)}
     *   <li>{@link MissingPasswordRequest#getEmail()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        MissingPasswordRequest actualMissingPasswordRequest = new MissingPasswordRequest("jane.doe@example.org");
        actualMissingPasswordRequest.setEmail("jane.doe@example.org");

        // Assert that nothing has changed
        assertEquals("jane.doe@example.org", actualMissingPasswordRequest.getEmail());
    }
}
