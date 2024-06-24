package com.example.bpiappapi.pm.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class OrganisationPartyIdentification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int organisationPartyIdentificationTypeGId;

    private String organisationPartyIdentificationValue;

    public OrganisationPartyRequest getOrganisationPartyRequest() {
        return organisationPartyRequest;
    }

    public void setOrganisationPartyRequest(OrganisationPartyRequest organisationPartyRequest) {
        this.organisationPartyRequest = organisationPartyRequest;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }
    private Date startingDate;

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public String getOrganisationPartyIdentificationValue() {
        return organisationPartyIdentificationValue;
    }

    public void setOrganisationPartyIdentificationValue(String organisationPartyIdentificationValue) {
        this.organisationPartyIdentificationValue = organisationPartyIdentificationValue;
    }

    public int getOrganisationPartyIdentificationTypeGId() {
        return organisationPartyIdentificationTypeGId;
    }

    public void setOrganisationPartyIdentificationTypeGId(int organisationPartyIdentificationTypeGId) {
        this.organisationPartyIdentificationTypeGId = organisationPartyIdentificationTypeGId;
    }
    private Date closingDate;

    @ManyToOne( cascade = CascadeType.ALL )
    @JoinColumn(name = "organisation_party_request_id")
    @JsonIgnore
    private OrganisationPartyRequest organisationPartyRequest;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

