package com.example.bpiappapi.auth.repository;


import com.example.bpiappapi.auth.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
@Transactional(readOnly = true)
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String email);


    @Transactional
    @Modifying
    @Query("UPDATE AppUser a SET a.enabled=true WHERE a.email=?1")
    int enableAppUser(String email);

    Optional<AppUser> findByEmailAndEnabled(String email, boolean enabled);}