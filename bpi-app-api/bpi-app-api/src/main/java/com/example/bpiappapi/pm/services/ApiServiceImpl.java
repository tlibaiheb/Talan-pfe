package com.example.bpiappapi.pm.services;

import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpEntity;
        import org.springframework.http.HttpHeaders;
        import org.springframework.http.HttpMethod;
        import org.springframework.http.ResponseEntity;
        import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiServiceImpl implements ApiService {

    private final RestTemplate restTemplate;

    @Autowired
    public ApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String getDataFromApi(String sirene, String bearerToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + bearerToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "https://staging.entreprise.api.gouv.fr/v3/insee/sirene/unites_legales/diffusibles/{sirene}/siege_social?context=Context de test&object=marché numéro 127&recipient=10000001700010",
                HttpMethod.GET,
                entity,
                String.class,
                sirene
        );

        return response.getBody();
    }
}
