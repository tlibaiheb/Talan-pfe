package com.example.bpiappapi.nomenclature.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String countryGId;
    private String countryLabel;
    private String internalBpiCode;
    private String iso2Code;
    private String iso3Code;
    private String nationalityLabel;
    private String numericalCode;
    private String phoneCountryCode;

    public static Country create(String countryGId, String countryLabel, String internalBpiCode, String iso2Code,
                                 String iso3Code, String nationalityLabel, String numericalCode, String phoneCountryCode) {
        return new Country(null, countryGId, countryLabel, internalBpiCode, iso2Code, iso3Code, nationalityLabel,
                numericalCode, phoneCountryCode);
    }

}