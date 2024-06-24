package com.example.bpiappapi.PM;

import com.example.bpiappapi.pm.models.AddressForm;
import com.example.bpiappapi.pm.models.OrganisationPartyRequest;
import com.example.bpiappapi.pm.reposotories.AddressRepository;
import com.example.bpiappapi.pm.reposotories.OrganisationPartyRepository;
import com.example.bpiappapi.pm.services.AddressServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
 class AddressServiceImplIntegrationTest {

    @Autowired
    private AddressServiceImpl addressService;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private OrganisationPartyRepository organisationPartyRepository;

    private OrganisationPartyRequest organisationPartyRequest;

    @BeforeEach
        void setUp() {
            // Préparer les données de test
        organisationPartyRequest = new OrganisationPartyRequest();
        organisationPartyRequest.setLegalName("Test Organisation");
        organisationPartyRequest = organisationPartyRepository.save(organisationPartyRequest);
    }

    @Test
     void testCreateAddress() {
        AddressForm addressForm = new AddressForm();
        addressForm.setLibelleDeLaVoie("123 Test St");
        addressForm.setTypeDeVoie("Test City");
        addressForm.setNum("12345");

        AddressForm createdAddress = addressService.createAddress(organisationPartyRequest.getId(), addressForm);

        assertNotNull(createdAddress);
        assertEquals("123 Test St", createdAddress.getLibelleDeLaVoie());
        assertEquals("Test City", createdAddress.getTypeDeVoie());
        assertEquals("12345", createdAddress.getNum());
        assertEquals(organisationPartyRequest.getId(), createdAddress.getOrganisationPartyRequest().getId());
    }

    @Test
     void testGetAddressById() {
        AddressForm addressForm = new AddressForm();
        addressForm.setLibelleDeLaVoie("123 Test St");
        addressForm.setTypeDeVoie("Test City");
        addressForm.setNum("12345");
        addressForm.setOrganisationPartyRequest(organisationPartyRequest);

        AddressForm savedAddress = addressRepository.save(addressForm);

        AddressForm fetchedAddress = addressService.getAdressById(savedAddress.getId());

        assertNotNull(fetchedAddress);
        assertEquals(savedAddress.getId(), fetchedAddress.getId());
        assertEquals("123 Test St", fetchedAddress.getLibelleDeLaVoie());
        assertEquals("Test City", fetchedAddress.getTypeDeVoie());
        assertEquals("12345", fetchedAddress.getNum());
    }
}
