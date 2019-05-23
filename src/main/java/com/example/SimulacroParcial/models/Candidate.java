package com.example.SimulacroParcial.models;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Candidate {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;
    private String surname;
    private String politicalParty;

    //Lazy:Trae solo lo que necesito. En caso de
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "candidate")
    @ToString.Exclude
    private List<Vote> votes;

}
