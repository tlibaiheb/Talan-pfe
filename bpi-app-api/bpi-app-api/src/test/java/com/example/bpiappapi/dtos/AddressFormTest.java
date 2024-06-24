package com.example.bpiappapi.dtos;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AddressFormTest {

    @Test
    void testGettersAndSetters() {
        // Given
        AddressForm addressForm = new AddressForm();
        String num = "123";
        String typeDeVoie = "Avenue";
        String libelleDeLaVoie = "Main";
        String complementAdresse = "Apt 1";
        String frLocationGId = "123456";

        // When
        addressForm.setNum(num);
        addressForm.setTypeDeVoie(typeDeVoie);
        addressForm.setLibelleDeLaVoie(libelleDeLaVoie);
        addressForm.setComplementAdresse(complementAdresse);
        addressForm.setFrLocationGId(frLocationGId);

        // Then
        assertThat(addressForm.getNum()).isEqualTo(num);
        assertThat(addressForm.getTypeDeVoie()).isEqualTo(typeDeVoie);
        assertThat(addressForm.getLibelleDeLaVoie()).isEqualTo(libelleDeLaVoie);
        assertThat(addressForm.getComplementAdresse()).isEqualTo(complementAdresse);
        assertThat(addressForm.getFrLocationGId()).isEqualTo(frLocationGId);
    }

    @Test
    void testEqualsAndHashCode() {
        // Given
        AddressForm addressForm1 = new AddressForm();
        addressForm1.setNum("123");
        addressForm1.setTypeDeVoie("Avenue");
        addressForm1.setLibelleDeLaVoie("Main");
        addressForm1.setComplementAdresse("Apt 1");
        addressForm1.setFrLocationGId("123456");

        AddressForm addressForm2 = new AddressForm();
        addressForm2.setNum("123");
        addressForm2.setTypeDeVoie("Avenue");
        addressForm2.setLibelleDeLaVoie("Main");
        addressForm2.setComplementAdresse("Apt 1");
        addressForm2.setFrLocationGId("123456");

        // When & Then
        assertThat(addressForm1).isEqualTo(addressForm2);
        assertThat(addressForm1.hashCode()).isEqualTo(addressForm2.hashCode());
    }

    @Test
    void testToString() {
        // Given
        AddressForm addressForm = new AddressForm();
        addressForm.setNum("123");
        addressForm.setTypeDeVoie("Avenue");
        addressForm.setLibelleDeLaVoie("Main");
        addressForm.setComplementAdresse("Apt 1");
        addressForm.setFrLocationGId("123456");

        // When
        String result = addressForm.toString();

        // Then
        assertThat(result).contains("num=123");
        assertThat(result).contains("typeDeVoie=Avenue");
        assertThat(result).contains("libelleDeLaVoie=Main");
        assertThat(result).contains("complementAdresse=Apt 1");
        assertThat(result).contains("frLocationGId=123456");
    }
}
