package com.example.bpiappapi.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "http://localhost:4200")

public class LogOutController {

    @GetMapping("/api/v1/logout")

    public ResponseEntity<?> logout(HttpServletResponse response, @CookieValue(value = "token", required = false) String token) {
        try {
            if (token != null) {
                Cookie cookie = new Cookie("token", null);
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }

            return ResponseEntity.ok().body("{\"message\": \"Déconnexion réussie\"}");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("{\"error\": \"Une erreur est survenue lors de la déconnexion\"}");
        }
    }
}
