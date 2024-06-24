package com.example.bpiappapi.pp.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "physical_contact_points")
public class PhysicalContactPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean inFrance  ;
    private String address1;
    private String address2;
    private String address3;
    private String address4;
    private String frLocationGId;
    private String countryGId;
    private String outOfFrTown;
    private String outOfFrPostCode;

    private int physicalContactPointTypeGId;
    private String num;
    private String typeDeVoie;
    private String libelleDeLaVoie;
    private String regionOutFr;
    private String complementAdresse;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_parties_id")
    @JsonIgnore

    private PersonParties personParties;

}