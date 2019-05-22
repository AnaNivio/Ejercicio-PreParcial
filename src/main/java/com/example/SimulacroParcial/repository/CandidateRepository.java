package com.example.SimulacroParcial.repository;

import com.example.SimulacroParcial.models.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//ver si cambiar el segundo parametro como votes
@Repository
public interface CandidateRepository extends JpaRepository<Candidate,Integer> {

}
