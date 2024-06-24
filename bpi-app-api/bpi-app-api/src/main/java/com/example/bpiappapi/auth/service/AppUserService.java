package com.example.bpiappapi.auth.service;


import com.example.bpiappapi.auth.model.AppUser;
import com.example.bpiappapi.auth.repository.AppUserRepository;
import com.example.bpiappapi.auth.security.PasswordEncoder;
import com.example.bpiappapi.auth.security.token.ConfirmationToken;
import com.example.bpiappapi.auth.security.token.ConfirmationTokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder, ConfirmationTokenService confirmationTokenService) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.confirmationTokenService = confirmationTokenService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email %s not found", email)));
    }
    public boolean doesUserExist(String email) {
        return appUserRepository.findByEmail(email).isPresent();
    }


    public String signUpUser(AppUser appUser) {
        boolean userExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();

        if (userExists) {

            AppUser appUserPrevious =  appUserRepository.findByEmail(appUser.getEmail()).get();
            Boolean isEnabled = appUserPrevious.getEnabled();

            if (!isEnabled) {
                String token = UUID.randomUUID().toString();

                //A method to save user and token in this class
                saveConfirmationToken(appUserPrevious, token);

                return token;

            }
            throw new IllegalStateException(String.format("User with email %s already exists!", appUser.getEmail()));
        }

        String encodedPassword = passwordEncoder.bCryptPasswordEncoder().encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);

        //Saving the user after encoding the password
        appUserRepository.save(appUser);

        //Creating a token from UUID
        String token = UUID.randomUUID().toString();

        //Getting the confirmation token and then saving it
        saveConfirmationToken(appUser, token);


        //Returning token
        return token;
    }


    private void saveConfirmationToken(AppUser appUser, String token) {
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15), appUser);
        confirmationTokenService.saveConfirmationToken(confirmationToken);
    }

    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);

    }

    public Optional<AppUser> findVerifiedUserByEmail(String email) {
        return appUserRepository.findByEmailAndEnabled(email,true);
    }


    private String secretKey="bef44646d60893d4c21bc693e3b5c79639d595e9262937fc8973969df7edde1c";

    public AppUser getUserFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        Long userId = Long.parseLong(claims.getSubject());
        return appUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
