package com.example.bpiappapi.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class CompanyFilledDTO {
    private String legalName;
    private int europeanOrganisationPartySizeGId;
    private String creationDate;
    private String legalCategoryGId;
    private AddressForm addressForm;
    private String nafCodeGId;
    private String registrationDate;

    private  String date_dernier_traitement ;

}
