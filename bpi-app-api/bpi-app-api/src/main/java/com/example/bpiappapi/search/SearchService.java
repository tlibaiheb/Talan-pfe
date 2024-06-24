package com.example.bpiappapi.search;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SearchService {

    @Value("${api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public SearchService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String searchByNomComplet(String nomComplet) {
        String url = apiUrl + "?q=" + nomComplet;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        // Extraire le numéro SIREN de la réponse JSON en utilisant une expression régulière
        String siren = extractSirenFromResponse(response.getBody());

        return siren;
    }

    private String extractSirenFromResponse(String responseBody) {
        // Expression régulière pour extraire le numéro SIREN du champ "siren" dans la réponse JSON
        String regex = "\"siren\"\\s*:\\s*\"(\\d+)\"";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(responseBody);

        // Si le numéro SIREN est trouvé, le retourner
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            // Si aucun numéro SIREN n'est trouvé, retourner une chaîne vide ou gérer l'erreur selon les besoins
            return "";
        }
    }
}
