package com.example.SimulacroParcial.models;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

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
    //@toStringExclude()
    private List<Vote> votes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Candidate)) return false;
        Candidate candidate = (Candidate) o;
        return Objects.equals(id, candidate.id) &&
                Objects.equals(name, candidate.name) &&
                Objects.equals(surname, candidate.surname) &&
                Objects.equals(politicalParty, candidate.politicalParty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, politicalParty);
    }
}
