package com.example.bpiappapi.auth.model;

public class MissingPasswordRequest {
    private String email;

    public MissingPasswordRequest() {
        // Constructeur par défaut requis par Jackson pour la désérialisation JSON
    }

    public MissingPasswordRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
