package com.example.bpiappapi.PM;

import com.example.bpiappapi.pm.controllers.AddressController;
import com.example.bpiappapi.pm.models.AddressForm;
import com.example.bpiappapi.pm.services.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class AddressControllerTest {

    @Mock
    private AddressService addressService;

    @InjectMocks
    private AddressController addressController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAddress() {
        long organisationPId = 1L;
        AddressForm addressForm = new AddressForm();
        when(addressService.createAddress(organisationPId, addressForm)).thenReturn(addressForm);

        ResponseEntity<AddressForm> response = addressController.createAddress(organisationPId, addressForm);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(addressForm);
    }

    @Test
    void testGetAddressById() {
        long id = 1L;
        AddressForm addressForm = new AddressForm();
        when(addressService.getAdressById(id)).thenReturn(addressForm);

        ResponseEntity<AddressForm> response = addressController.getAddressyById(id);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(addressForm);
    }
}
