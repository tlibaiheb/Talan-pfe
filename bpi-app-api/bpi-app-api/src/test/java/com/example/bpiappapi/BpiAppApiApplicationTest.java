package com.example.bpiappapi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

class BpiAppApiApplicationTest {




    @Test
    void testRestTemplateCreation() {
        BpiAppApiApplication application = new BpiAppApiApplication();
        RestTemplate restTemplate = application.restTemplate();
        assertNotNull(restTemplate);
    }

    @Test
    void testSpringAnnotations() {
        assertTrue(BpiAppApiApplication.class.isAnnotationPresent(SpringBootApplication.class));
        assertTrue(BpiAppApiApplication.class.isAnnotationPresent(EnableScheduling.class));
    }

    @Test
    void testMainMethod() {
        // Exécutez la méthode main directement
        String[] args = {}; // aucun argument supplémentaire requis
        BpiAppApiApplication.main(args);
        // Si la méthode main s'exécute sans lever d'exception, le test réussit
    }
}

