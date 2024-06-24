package com.example.bpiappapi.auth.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {LogOutController.class})
@ExtendWith(SpringExtension.class)
class LogOutControllerTest {
    @Autowired
    private LogOutController logOutController;

    /**
     * Method under test:
     * {@link LogOutController#logout(HttpServletResponse, String)}
     */
    @Test
    void testLogout() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/logout");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(logOutController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("{\"message\": \"Déconnexion réussie\"}"));
    }

    /**
     * Method under test:
     * {@link LogOutController#logout(HttpServletResponse, String)}
     */
    @Test
    void testLogout2() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/logout", "Uri Vars");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(logOutController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("{\"message\": \"Déconnexion réussie\"}"));
    }

    /**
     * Method under test:
     * {@link LogOutController#logout(HttpServletResponse, String)}
     */
    @Test
    void testLogout3() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/logout");
        MockHttpServletRequestBuilder requestBuilder = getResult.cookie(new Cookie("token", "ABC123"));

        // Act and Assert
        ResultActions resultActions = MockMvcBuilders.standaloneSetup(logOutController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("{\"message\": \"Déconnexion réussie\"}"));
        ResultActions resultActions2 = resultActions
                .andExpect(MockMvcResultMatchers.cookie().value("token", (String) null));
        ResultActions resultActions3 = resultActions2.andExpect(MockMvcResultMatchers.cookie().secure("token", false));
        ResultActions resultActions4 = resultActions3.andExpect(MockMvcResultMatchers.cookie().httpOnly("token", false));
        resultActions4.andExpect(MockMvcResultMatchers.cookie().maxAge("token", 0));
    }
}
