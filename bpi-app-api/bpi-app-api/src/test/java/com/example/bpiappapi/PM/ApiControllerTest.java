package com.example.bpiappapi.PM;

import com.example.bpiappapi.pm.services.ApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

class ApiControllerTest {

    @Mock
    private ApiService apiService;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetData() {
        String sirene = "123456789"; // Valeur de test pour le numéro sirene
        String responseFromApi = "Data from API"; // Réponse simulée de l'API
        String bearerToken = "BearerToken"; // Token d'authentification simulé
        when(apiService.getDataFromApi(sirene, bearerToken)).thenReturn(responseFromApi);



    }

}
