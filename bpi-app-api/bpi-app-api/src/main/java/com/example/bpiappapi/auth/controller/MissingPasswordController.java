package com.example.bpiappapi.auth.controller;

import com.example.bpiappapi.auth.model.MissingPasswordRequest;
import com.example.bpiappapi.auth.model.ResetPasswordRequest;
import com.example.bpiappapi.auth.service.MissingPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")

public class MissingPasswordController {

    private MissingPasswordService missingPasswordService;

    @Autowired
    public MissingPasswordController(MissingPasswordService missingPasswordService) {
        this.missingPasswordService = missingPasswordService;
    }

    @PostMapping("/api/v1/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody MissingPasswordRequest request) {
        try {
            missingPasswordService.sendEmailToUser(request.getEmail());
            return ResponseEntity.ok().body("Un email contenant votre adresse e-mail a été envoyé.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite lors de l'envoi de l'email.");
        }
    }


    @PostMapping("/api/v1/reset-password")
    public ResponseEntity<Map<String, String>> resetPassword(@RequestBody ResetPasswordRequest request) {
        Map<String, String> responseBody = new HashMap<>();
        try {
            missingPasswordService.resetPassword(request.getToken(), request.getNewPassword());
            responseBody.put("message", "Le mot de passe a été réinitialisé avec succès.");
            return ResponseEntity.ok().body(responseBody);
        } catch (IllegalArgumentException e) {
            responseBody.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
        } catch (Exception e) {
            responseBody.put("error", "Une erreur s'est produite lors de la réinitialisation du mot de passe.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
        }
    }

}
