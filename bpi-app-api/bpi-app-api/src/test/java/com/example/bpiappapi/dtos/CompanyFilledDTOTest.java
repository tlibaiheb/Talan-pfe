package com.example.bpiappapi.dtos;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CompanyFilledDTOTest {

    @Test
    void testGettersAndSetters() {
        // Given
        CompanyFilledDTO company = new CompanyFilledDTO();
        AddressForm addressForm = new AddressForm();
        String legalName = "Test Legal Name";
        int europeanOrganisationPartySizeGId = 1;
        String creationDate = "2023-05-20";
        String legalCategoryGId = "LCG123";
        String nafCodeGId = "N123";
        String registrationDate = "2023-06-20";
        String dateDernierTraitement = "2024-05-20";

        // When
        company.setLegalName(legalName);
        company.setEuropeanOrganisationPartySizeGId(europeanOrganisationPartySizeGId);
        company.setCreationDate(creationDate);
        company.setLegalCategoryGId(legalCategoryGId);
        company.setAddressForm(addressForm);
        company.setNafCodeGId(nafCodeGId);
        company.setRegistrationDate(registrationDate);
        company.setDate_dernier_traitement(dateDernierTraitement);

        // Then
        assertThat(company.getLegalName()).isEqualTo(legalName);
        assertThat(company.getEuropeanOrganisationPartySizeGId()).isEqualTo(europeanOrganisationPartySizeGId);
        assertThat(company.getCreationDate()).isEqualTo(creationDate);
        assertThat(company.getLegalCategoryGId()).isEqualTo(legalCategoryGId);
        assertThat(company.getAddressForm()).isEqualTo(addressForm);
        assertThat(company.getNafCodeGId()).isEqualTo(nafCodeGId);
        assertThat(company.getRegistrationDate()).isEqualTo(registrationDate);
        assertThat(company.getDate_dernier_traitement()).isEqualTo(dateDernierTraitement);
    }

    @Test
    void testEqualsAndHashCode() {
        // Given
        AddressForm addressForm = new AddressForm();
        CompanyFilledDTO company1 = new CompanyFilledDTO();
        company1.setLegalName("Test Legal Name");
        company1.setEuropeanOrganisationPartySizeGId(1);
        company1.setCreationDate("2023-05-20");
        company1.setLegalCategoryGId("LCG123");
        company1.setAddressForm(addressForm);
        company1.setNafCodeGId("N123");
        company1.setRegistrationDate("2023-06-20");
        company1.setDate_dernier_traitement("2024-05-20");

        CompanyFilledDTO company2 = new CompanyFilledDTO();
        company2.setLegalName("Test Legal Name");
        company2.setEuropeanOrganisationPartySizeGId(1);
        company2.setCreationDate("2023-05-20");
        company2.setLegalCategoryGId("LCG123");
        company2.setAddressForm(addressForm);
        company2.setNafCodeGId("N123");
        company2.setRegistrationDate("2023-06-20");
        company2.setDate_dernier_traitement("2024-05-20");

        // When & Then
        assertThat(company1).isEqualTo(company2);
        assertThat(company1.hashCode()).isEqualTo(company2.hashCode());
    }

    @Test
    void testToString() {
        // Given
        AddressForm addressForm = new AddressForm();
        CompanyFilledDTO company = new CompanyFilledDTO();
        company.setLegalName("Test Legal Name");
        company.setEuropeanOrganisationPartySizeGId(1);
        company.setCreationDate("2023-05-20");
        company.setLegalCategoryGId("LCG123");
        company.setAddressForm(addressForm);
        company.setNafCodeGId("N123");
        company.setRegistrationDate("2023-06-20");
        company.setDate_dernier_traitement("2024-05-20");

        // When
        String result = company.toString();

        // Then
        assertThat(result).contains("legalName=Test Legal Name");
        assertThat(result).contains("europeanOrganisationPartySizeGId=1");
        assertThat(result).contains("creationDate=2023-05-20");
        assertThat(result).contains("legalCategoryGId=LCG123");
        assertThat(result).contains("addressForm");
        assertThat(result).contains("nafCodeGId=N123");
        assertThat(result).contains("registrationDate=2023-06-20");
        assertThat(result).contains("date_dernier_traitement=2024-05-20");
    }
}
