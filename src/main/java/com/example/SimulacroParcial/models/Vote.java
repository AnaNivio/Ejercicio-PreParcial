package com.example.SimulacroParcial.models;
//hay un problema con votos

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static java.util.Objects.isNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vote {

    @Id
    //se agrega el (strategy = GenerationType.IDENTITY) para que no siga la secuencia de valores generados de candidatos
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime votesDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_candidate",referencedColumnName = "id")
    @JsonIgnore
    private Candidate candidate;

    @PrePersist//hace que esto sea lo primero que corra al crear el objeto
    public void addDate(){
        if (isNull(this.votesDate)){
            this.votesDate=LocalDateTime.now();
        }
    }

}
