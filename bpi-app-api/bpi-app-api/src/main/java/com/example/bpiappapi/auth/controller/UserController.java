package com.example.bpiappapi.auth.controller;

import com.example.bpiappapi.auth.model.AppUser;
import com.example.bpiappapi.auth.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final AppUserService appUserService;

    @Autowired
    public UserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/api/v1/user")
    public AppUser getUserFromToken(@RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7); // Extrait le token de l'en-tête
            return appUserService.getUserFromToken(token);
        } else {
            throw new IllegalArgumentException("Authorization header is missing or invalid");
        }
    }
}

