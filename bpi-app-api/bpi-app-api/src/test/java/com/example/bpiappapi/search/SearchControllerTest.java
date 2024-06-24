package com.example.bpiappapi.search;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class SearchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SearchService searchService;

    @BeforeEach
    void setUp() {
        // Configure mock behavior
        when(searchService.searchByNomComplet("John Doe")).thenReturn("123456789");
    }

    @Test
    void searchByNomComplet() throws Exception {
        // Perform GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/search")
                        .param("nomComplet", "John Doe"))
                // Verify response
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("123456789"));
    }
}
