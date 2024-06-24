package com.example.bpiappapi.pp.models;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "person_parties")
public class PersonParties {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @NotNull
    @Column(name = "name_prefix_gid")
    private int namePrefixGId;
    @NotEmpty
    @Size(max = 150, message = "Le champ birthName doit avoir au maximum 150 caractères")
    @Column(name = "birth_name")
    private String birthName;
    @NotEmpty
    @Size(max = 150, message = "Le champ mainFirstName doit avoir au maximum 150 caractères")
    @Column(name = "main_first_name")
    private String mainFirstName;
    @Size(max = 150)
    @Column(name = "first_name")
    private String firstNames;
    @NotNull
    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    @Column(name = "id_tiers")
    private String idTiers;



    @NotEmpty
    @Column(name = "country_of_birth_gid")
    private String countryOfBirthGId;
    @Column(name = "use_name")
    private String useName ;

    @Column(name = "cp_number")
    private String cpNumber ;

    private String outOfFrBirthPostCode;
    private String outOfFrPlaceOfBirth;
    private String frPlaceOfBirthGId ;

    @OneToOne(mappedBy = "personParties", cascade = CascadeType.ALL)
    private PhysicalContactPoint physicalContactPoint;

}
