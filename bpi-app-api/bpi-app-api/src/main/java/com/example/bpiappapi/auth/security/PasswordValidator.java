package com.example.bpiappapi.auth.security;



import org.springframework.stereotype.Service;

import java.util.function.Predicate;
@Service
public class PasswordValidator implements Predicate<String> {

    @Override
    public boolean test(String password) {

        return password != null && password.length() >= 8 && password.matches(".*\\d.*") && password.matches(".*[!@#$%^&*()].*");
    }
}

