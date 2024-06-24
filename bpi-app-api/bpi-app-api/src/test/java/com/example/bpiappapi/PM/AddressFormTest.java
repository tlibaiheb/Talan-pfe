package com.example.bpiappapi.PM;

import com.example.bpiappapi.pm.models.AddressForm;
import com.example.bpiappapi.pm.models.OrganisationPartyRequest;
import org.junit.jupiter.api.Test;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static javax.validation.Validation.buildDefaultValidatorFactory;
import static org.junit.jupiter.api.Assertions.*;

class AddressFormTest {

    @Test
     void testAddressFormBuilderAndGettersSetters() {
        AddressForm addressForm = AddressForm.builder()
                .id(1L)
                .num("123")
                .typeDeVoie("Rue")
                .libelleDeLaVoie("de Rivoli")
                .complementAdresse("Apt 4")
                .frLocationGId("75001")
                .organisationPartyRequest(new OrganisationPartyRequest())
                .build();

        assertNotNull(addressForm);
        assertEquals(1L, addressForm.getId());
        assertEquals("123", addressForm.getNum());
        assertEquals("Rue", addressForm.getTypeDeVoie());
        assertEquals("de Rivoli", addressForm.getLibelleDeLaVoie());
        assertEquals("Apt 4", addressForm.getComplementAdresse());
        assertEquals("75001", addressForm.getFrLocationGId());
        assertNotNull(addressForm.getOrganisationPartyRequest());
    }
    @Test
    void testEqualsAndHashCode() {
        AddressForm addressForm1 = AddressForm.builder()
                .id(1L)
                .build();

        AddressForm addressForm2 = AddressForm.builder()
                .id(1L)
                .build();

        AddressForm addressForm3 = AddressForm.builder()
                .id(2L)
                .build();

        assertEquals(addressForm1, addressForm2);
        assertNotEquals(addressForm1, addressForm3);
        assertEquals(addressForm1.hashCode(), addressForm2.hashCode());
        assertNotEquals(addressForm1.hashCode(), addressForm3.hashCode());
    }

    @Test
    void testToString() {
        AddressForm addressForm = AddressForm.builder()
                .id(1L)
                .num("123")
                .typeDeVoie("Rue")
                .libelleDeLaVoie("de Rivoli")
                .complementAdresse("Apt 4")
                .frLocationGId("75001")
                .organisationPartyRequest(new OrganisationPartyRequest())
                .build();

        String expectedToString = "AddressForm(id=1, num=123, typeDeVoie=Rue, libelleDeLaVoie=de Rivoli, complementAdresse=Apt 4, frLocationGId=75001, organisationPartyRequest=" + addressForm.getOrganisationPartyRequest().toString() + ")";
        assertEquals(expectedToString, addressForm.toString());
    }
    @Test
    void testSetters() {
        AddressForm addressForm = new AddressForm();
        addressForm.setId(1L);
        addressForm.setComplementAdresse("Apt 4");
        addressForm.setFrLocationGId("75001");

        assertEquals(1L, addressForm.getId());
        assertEquals("Apt 4", addressForm.getComplementAdresse());
        assertEquals("75001", addressForm.getFrLocationGId());
    }



}
