package com.example.bpiappapi.pm.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class AddressForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String num;
    private String typeDeVoie;
    private String libelleDeLaVoie;
    private String complementAdresse;
    //ville
    @NotEmpty
    private String frLocationGId;

    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "organisation_party_request_id")
    @JsonIgnore
    private OrganisationPartyRequest organisationPartyRequest;


}

