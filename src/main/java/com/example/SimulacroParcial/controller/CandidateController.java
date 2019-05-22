package com.example.SimulacroParcial.controller;

import com.example.SimulacroParcial.models.Candidate;
import com.example.SimulacroParcial.models.Vote;
import com.example.SimulacroParcial.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@RequestMapping("/candidates")
@RestController
public class CandidateController {
    //PathVariable:objetos que se pasan por url
    //RequestBody:objeto que es pedido en el postman

    @Autowired
    private CandidateRepository candidateRepository;

    @PostMapping("") // La combinacion de un verbo(post/get) y el mapeo. Ej /add seria: localhost:8080/person/add
    private void addCandidate(@RequestBody Candidate c){
        candidateRepository.save(c);
    }

    /**
     *
     * @return status of the microservices.
     */
    @GetMapping("")
    private List<Candidate> getAll(){
        return candidateRepository.findAll();
    }


    @GetMapping("/{id}" )
    private Optional<Candidate> getCandidateById(@PathVariable Integer id){

        return candidateRepository.findById(id);
    }

    //SE DEBE RESPETAR EL ORDEN DE LOS PARAMETROS DEL POSTMAPPING CON LOS PARAMETROS DEL METODO AL QUE AFECTA
    @PostMapping("/{id}/vote")
    private void voteCandidate(@PathVariable final Integer id, @RequestBody final Vote vote){

        Optional<Candidate> cOptional= getCandidateById(id);
        Candidate c= cOptional.get();


        vote.setCandidate(c);
        c.getVotes().add(vote);

        candidateRepository.save(c);//no se hace un update porque, dentro del save, se encuentra el codigo para actualizar datos
    }

    //[GET] /votes devolver candidatos con sus respectivos votos. <-- esto es lo mismo que el metodo get all
    //a menos que se refiera a eso. Puede ser que esto no ande por tener dos getmapping que hacen lo mismo?
    /*
    @GetMapping("/{id}")
    private List<Vote> getAllVotesByCandidate(@PathVariable Integer id){

        Optional<Candidate> cOptional= getCandidateById(id);
        Candidate c= cOptional.get();
        List<Vote> votes= c.getVotes();

        return votes;
    }*/


}
