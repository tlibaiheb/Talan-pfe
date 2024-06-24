package com.example.bpiappapi.auth.service;

import com.example.bpiappapi.auth.security.token.ConfirmationToken;
import com.example.bpiappapi.auth.security.token.ConfirmationTokenService;
import io.jsonwebtoken.security.Keys;
import com.example.bpiappapi.auth.security.token.ConfirmationToken;
import com.example.bpiappapi.auth.security.token.ConfirmationTokenService;

import com.example.bpiappapi.auth.model.AppUser;
import com.example.bpiappapi.auth.repository.AppUserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Service
public class LoginService {

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;


    @Autowired
    public LoginService(AppUserRepository appUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder, ConfirmationTokenService confirmationTokenService) {
        this.appUserRepository = appUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.confirmationTokenService = confirmationTokenService;

    }

    public String login(String email, String password) {
        AppUser user = appUserRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.isEnabled()) {
            throw new IllegalStateException("User is not verified");
        }

        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Incorrect password");
        }

        // Générer le token JWT ici
        String token=generateToken(user);
        return token;

    }

    private String generateToken(AppUser user) {
        long expirationTimeInMilliseconds = 864_000_000;

        byte[] secretKeyBytes = Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded();
        String secretKey = "bef44646d60893d4c21bc693e3b5c79639d595e9262937fc8973969df7edde1c";
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expirationTimeInMilliseconds);

        return Jwts.builder()
                .setSubject(Long.toString(user.getId()))
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}


