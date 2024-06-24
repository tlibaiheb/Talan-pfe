package com.example.bpiappapi.auth.security.token;

import com.example.bpiappapi.auth.security.PasswordValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class PasswordValidatorTest {

    @ParameterizedTest
    @CsvSource({
            "Password123!, true", // Valide
            "Pass123!, false", // Trop court
            "Password!, false", // Pas de chiffre
            "Password123, false", // Pas de caractère spécial
            "null, false" // Null
    })
    void testPasswordValidity(String password, boolean expectedResult) {
        // Given
        PasswordValidator passwordValidator = new PasswordValidator();

        // When
        boolean isValid = passwordValidator.test(password);

        // Then
    }
}
