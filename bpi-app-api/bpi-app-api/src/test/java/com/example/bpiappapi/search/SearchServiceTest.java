package com.example.bpiappapi.search;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestPropertySource(properties = {"api.url=https://recherche-entreprises.api.gouv.fr/search"})
public class SearchServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private SearchService searchService;

    @Test
    void searchByNomComplet_Success() {
        String responseBody = "{\"siren\":\"123456789\"}";
        when(restTemplate.exchange(any(String.class), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class)))
                .thenReturn(new ResponseEntity<>(responseBody, HttpStatus.OK));

        String siren = searchService.searchByNomComplet("Example");

        assertEquals("123456789", siren);
    }

    @Test
    void searchByNomComplet_NoSirenFound() {
        String responseBody = "{}";
        when(restTemplate.exchange(any(String.class), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class)))
                .thenReturn(new ResponseEntity<>(responseBody, HttpStatus.OK));

        String siren = searchService.searchByNomComplet("Example");

        assertEquals("", siren);
    }
}

