package com.example.SimulacroParcial.repository;

import com.example.SimulacroParcial.models.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VoteRepository extends JpaRepository<Candidate,Integer> {

    @Query("DELETE FROM Vote WHERE MINUTE(NOW()) - MINUTE(votesDate) =5;")
    void deleteVotesInFiveMinutes();
}
