package com.example.bpiappapi.pm.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class OrganisationPartyRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Numero(s) CP associé(s)
     private String CP;


    //libelle commerciale
    private String tradingName;


    @Temporal(TemporalType.DATE)
    private Date creationDate;

    //taille d'entreprise
    @NotNull
    private int europeanOrganisationPartySizeGId;


    // idTiers
    @Column(unique = true)
    private String idTiers;


    private String gicsCodeGId;


    //category juridique
    @NotEmpty
    private String legalCategoryGId;


    @NotEmpty
    @Size(max = 150)
    //raison social
    private String legalName;

    @Pattern(regexp = "^[0-9]{9}$", message = "Le numéro SIREN doit contenir exactement 9 chiffres")
    private String siren;
    private int missingSIRENSIRETJustificationGId;

    @NotEmpty
    private String nafCodeGId;

    //private int organisationPartyTypeGId;

    @Temporal(TemporalType.DATE)
    private Date registrationDate;

    private  String date_dernier_traitement ;
    @Pattern(regexp = "^[0-9 ]*$", message = "Le capital social doit contenir uniquement des chiffres et des espaces")
    //Mentant capital social (en£)
    private String shareCapital;


    //LEI
    @OneToMany(mappedBy = "organisationPartyRequest", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrganisationPartyIdentification> organisationPartyIdentifications;

    @OneToOne(mappedBy = "organisationPartyRequest", cascade = CascadeType.ALL)
    private AddressForm addressForm;


    @Override
    public String toString() {
        return "OrganisationPartyRequest{" +
                "id=" + id +
                ", CP='" + CP + '\'' +
                ", tradingName='" + tradingName + '\'' +
                ", creationDate=" + creationDate +
                ", europeanOrganisationPartySizeGId=" + europeanOrganisationPartySizeGId +
                ", gicsCodeGId='" + gicsCodeGId + '\'' +
                ", legalCategoryGId='" + legalCategoryGId + '\'' +
                ", legalName='" + legalName + '\'' +
                ", missingSIRENSIRETJustificationGId=" + missingSIRENSIRETJustificationGId +
                ", nafCodeGId='" + nafCodeGId + '\'' +
                ", registrationDate=" + registrationDate +
                ", shareCapital='" + shareCapital + '\'' +
                '}';
    }

    public String generateIdTiers() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }



}
