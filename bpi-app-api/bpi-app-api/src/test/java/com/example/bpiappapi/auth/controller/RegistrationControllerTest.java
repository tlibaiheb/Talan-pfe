package com.example.bpiappapi.auth.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.bpiappapi.auth.email.EmailSender;
import com.example.bpiappapi.auth.model.AppUser;
import com.example.bpiappapi.auth.model.AppUserRole;
import com.example.bpiappapi.auth.model.RegistrationRequest;
import com.example.bpiappapi.auth.repository.AppUserRepository;
import com.example.bpiappapi.auth.security.EmailValidator;
import com.example.bpiappapi.auth.security.PasswordEncoder;
import com.example.bpiappapi.auth.security.PasswordValidator;
import com.example.bpiappapi.auth.security.token.ConfirmationTokenRepository;
import com.example.bpiappapi.auth.security.token.ConfirmationTokenService;
import com.example.bpiappapi.auth.service.AppUserService;
import com.example.bpiappapi.auth.service.RegistrationService;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {RegistrationController.class})
@ExtendWith(SpringExtension.class)
class RegistrationControllerTest {
    @Autowired
    private RegistrationController registrationController;

    @MockBean
    private RegistrationService registrationService;

    /**
     * Method under test: {@link RegistrationController#confirm(String)}
     */
    @Test
    void testConfirm() throws Exception {
        // Arrange
        when(registrationService.confirmToken(Mockito.<String>any())).thenReturn("ABC123");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/registration/confirm")
                .param("token", "foo");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(registrationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("ABC123"));
    }

    /**
     * Method under test:
     * {@link RegistrationController#register(RegistrationRequest)}
     */
    @Test
    void testRegister() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        AppUser appUser = new AppUser();
        appUser.setAppUserRole(AppUserRole.USER);
        appUser.setEmail("jane.doe@example.org");
        appUser.setEnabled(true);
        appUser.setFirstName("Jane");
        appUser.setId(1L);
        appUser.setLastName("Doe");
        appUser.setLocked(true);
        appUser.setPassword("iloveyou");
        Optional<AppUser> ofResult = Optional.of(appUser);
        AppUserRepository appUserRepository = mock(AppUserRepository.class);
        when(appUserRepository.findByEmail(Mockito.<String>any())).thenReturn(ofResult);
        PasswordEncoder passwordEncoder = new PasswordEncoder();
        AppUserService appUserService = new AppUserService(appUserRepository, passwordEncoder,
                new ConfirmationTokenService(mock(ConfirmationTokenRepository.class)));

        EmailValidator emailValidator = new EmailValidator();
        PasswordValidator passwordValidator = new PasswordValidator();
        RegistrationController registrationController = new RegistrationController(
                new RegistrationService(appUserService, emailValidator, passwordValidator,
                        new ConfirmationTokenService(mock(ConfirmationTokenRepository.class)), mock(EmailSender.class)));

        // Act
        ResponseEntity<?> actualRegisterResult = registrationController
                .register(new RegistrationRequest("Jane", "Doe", "jane.doe@example.org", "iloveyou"));

        // Assert
        verify(appUserRepository).findByEmail(eq("jane.doe@example.org"));
        assertEquals("Email already exists", actualRegisterResult.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, actualRegisterResult.getStatusCode());
        assertTrue(actualRegisterResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link RegistrationController#register(RegistrationRequest)}
     */
    @Test
    void testRegister2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        AppUserRepository appUserRepository = mock(AppUserRepository.class);
        Optional<AppUser> emptyResult = Optional.empty();
        when(appUserRepository.findByEmail(Mockito.<String>any())).thenReturn(emptyResult);
        PasswordEncoder passwordEncoder = new PasswordEncoder();
        AppUserService appUserService = new AppUserService(appUserRepository, passwordEncoder,
                new ConfirmationTokenService(mock(ConfirmationTokenRepository.class)));

        EmailValidator emailValidator = new EmailValidator();
        PasswordValidator passwordValidator = new PasswordValidator();
        RegistrationController registrationController = new RegistrationController(
                new RegistrationService(appUserService, emailValidator, passwordValidator,
                        new ConfirmationTokenService(mock(ConfirmationTokenRepository.class)), mock(EmailSender.class)));

        // Act
        ResponseEntity<?> actualRegisterResult = registrationController
                .register(new RegistrationRequest("Jane", "Doe", "jane.doe@example.org", "iloveyou"));

        // Assert
        verify(appUserRepository).findByEmail(eq("jane.doe@example.org"));
        assertEquals("Password does not meet the criteria", actualRegisterResult.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, actualRegisterResult.getStatusCode());
        assertTrue(actualRegisterResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link RegistrationController#register(RegistrationRequest)}
     */
    @Test
    void testRegister3() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        AppUser appUser = new AppUser();
        appUser.setAppUserRole(AppUserRole.USER);
        appUser.setEmail("jane.doe@example.org");
        appUser.setEnabled(true);
        appUser.setFirstName("Jane");
        appUser.setId(1L);
        appUser.setLastName("Doe");
        appUser.setLocked(true);
        appUser.setPassword("iloveyou");
        Optional<AppUser> ofResult = Optional.of(appUser);
        AppUserRepository appUserRepository = mock(AppUserRepository.class);
        when(appUserRepository.findByEmail(Mockito.<String>any())).thenReturn(ofResult);
        PasswordEncoder passwordEncoder = new PasswordEncoder();
        AppUserService appUserService = new AppUserService(appUserRepository, passwordEncoder,
                new ConfirmationTokenService(mock(ConfirmationTokenRepository.class)));

        EmailValidator emailValidator = new EmailValidator();
        PasswordValidator passwordValidator = new PasswordValidator();
        RegistrationController registrationController = new RegistrationController(
                new RegistrationService(appUserService, emailValidator, passwordValidator,
                        new ConfirmationTokenService(mock(ConfirmationTokenRepository.class)), mock(EmailSender.class)));

        // Act
        ResponseEntity<?> actualRegisterResult = registrationController
                .register(new RegistrationRequest("Jane", "Doe", "john.smith@example.org", "iloveyou"));

        // Assert
        verify(appUserRepository).findByEmail(eq("john.smith@example.org"));
        assertEquals("Email already exists", actualRegisterResult.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, actualRegisterResult.getStatusCode());
        assertTrue(actualRegisterResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link RegistrationController#register(RegistrationRequest)}
     */
    @Test
    void testRegister4() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        AppUser appUser = new AppUser();
        appUser.setAppUserRole(AppUserRole.USER);
        appUser.setEmail("jane.doe@example.org");
        appUser.setEnabled(true);
        appUser.setFirstName("Jane");
        appUser.setId(1L);
        appUser.setLastName("Doe");
        appUser.setLocked(true);
        appUser.setPassword("iloveyou");
        Optional<AppUser> ofResult = Optional.of(appUser);
        AppUserRepository appUserRepository = mock(AppUserRepository.class);
        when(appUserRepository.findByEmail(Mockito.<String>any())).thenReturn(ofResult);
        PasswordEncoder passwordEncoder = new PasswordEncoder();
        AppUserService appUserService = new AppUserService(appUserRepository, passwordEncoder,
                new ConfirmationTokenService(mock(ConfirmationTokenRepository.class)));

        EmailValidator emailValidator = new EmailValidator();
        PasswordValidator passwordValidator = new PasswordValidator();
        RegistrationController registrationController = new RegistrationController(
                new RegistrationService(appUserService, emailValidator, passwordValidator,
                        new ConfirmationTokenService(mock(ConfirmationTokenRepository.class)), mock(EmailSender.class)));

        // Act
        ResponseEntity<?> actualRegisterResult = registrationController
                .register(new RegistrationRequest("Jane", "Doe", "prof.einstein@example.org", "iloveyou"));

        // Assert
        verify(appUserRepository).findByEmail(eq("prof.einstein@example.org"));
        assertEquals("Email already exists", actualRegisterResult.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, actualRegisterResult.getStatusCode());
        assertTrue(actualRegisterResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link RegistrationController#register(RegistrationRequest)}
     */
    @Test
    void testRegister5() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        AppUser appUser = new AppUser();
        appUser.setAppUserRole(AppUserRole.USER);
        appUser.setEmail("jane.doe@example.org");
        appUser.setEnabled(true);
        appUser.setFirstName("Jane");
        appUser.setId(1L);
        appUser.setLastName("Doe");
        appUser.setLocked(true);
        appUser.setPassword("iloveyou");
        Optional<AppUser> ofResult = Optional.of(appUser);
        AppUserRepository appUserRepository = mock(AppUserRepository.class);
        when(appUserRepository.findByEmail(Mockito.<String>any())).thenReturn(ofResult);
        PasswordEncoder passwordEncoder = new PasswordEncoder();
        AppUserService appUserService = new AppUserService(appUserRepository, passwordEncoder,
                new ConfirmationTokenService(mock(ConfirmationTokenRepository.class)));

        EmailValidator emailValidator = new EmailValidator();
        PasswordValidator passwordValidator = new PasswordValidator();
        RegistrationController registrationController = new RegistrationController(
                new RegistrationService(appUserService, emailValidator, passwordValidator,
                        new ConfirmationTokenService(mock(ConfirmationTokenRepository.class)), mock(EmailSender.class)));

        // Act
        ResponseEntity<?> actualRegisterResult = registrationController
                .register(new RegistrationRequest("Jane", "Doe", "U'U'U@U-U-U.U-U-U", "iloveyou"));

        // Assert
        verify(appUserRepository).findByEmail(eq("U'U'U@U-U-U.U-U-U"));
        assertEquals("Email already exists", actualRegisterResult.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, actualRegisterResult.getStatusCode());
        assertTrue(actualRegisterResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link RegistrationController#register(RegistrationRequest)}
     */
    @Test
    void testRegister6() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        AppUserRepository appUserRepository = mock(AppUserRepository.class);
        Optional<AppUser> emptyResult = Optional.empty();
        when(appUserRepository.findByEmail(Mockito.<String>any())).thenReturn(emptyResult);
        PasswordEncoder passwordEncoder = new PasswordEncoder();
        AppUserService appUserService = new AppUserService(appUserRepository, passwordEncoder,
                new ConfirmationTokenService(mock(ConfirmationTokenRepository.class)));

        EmailValidator emailValidator = new EmailValidator();
        PasswordValidator passwordValidator = new PasswordValidator();
        RegistrationController registrationController = new RegistrationController(
                new RegistrationService(appUserService, emailValidator, passwordValidator,
                        new ConfirmationTokenService(mock(ConfirmationTokenRepository.class)), mock(EmailSender.class)));

        // Act
        ResponseEntity<?> actualRegisterResult = registrationController
                .register(new RegistrationRequest("Jane", "Doe", "jane.doe@example.org", "U9U"));

        // Assert
        verify(appUserRepository).findByEmail(eq("jane.doe@example.org"));
        assertEquals("Password does not meet the criteria", actualRegisterResult.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, actualRegisterResult.getStatusCode());
        assertTrue(actualRegisterResult.getHeaders().isEmpty());
    }
}
