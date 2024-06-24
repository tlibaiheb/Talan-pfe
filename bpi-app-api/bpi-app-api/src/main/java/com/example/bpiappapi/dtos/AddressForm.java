package com.example.bpiappapi.dtos;

import lombok.Data;

@Data
public class AddressForm {
    private String num;
    private String typeDeVoie;
    private String libelleDeLaVoie;
    private String complementAdresse;
    private String frLocationGId;
}
