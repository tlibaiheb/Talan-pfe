package com.example.bpiappapi.PM;

import com.example.bpiappapi.pm.services.ApiServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ApiServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ApiServiceImpl apiService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetDataFromApi() {
        String sirene = "123456789";
        String bearerToken = "testBearerToken";
        String expectedResponse = "{ \"data\": \"some data\" }";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + bearerToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> responseEntity = new ResponseEntity<>(expectedResponse, HttpStatus.OK);

        when(restTemplate.exchange(
                anyString(),
                eq(HttpMethod.GET),
                eq(entity),
                eq(String.class),
                eq(sirene)
        )).thenReturn(responseEntity);

        String actualResponse = apiService.getDataFromApi(sirene, bearerToken);

        assertEquals(expectedResponse, actualResponse);

        verify(restTemplate, times(1)).exchange(
                anyString(),
                eq(HttpMethod.GET),
                eq(entity),
                eq(String.class),
                eq(sirene)
        );
    }
}
