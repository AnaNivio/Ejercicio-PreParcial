package com.example.SimulacroParcial.controller;

import com.example.SimulacroParcial.models.Candidate;
import com.example.SimulacroParcial.models.Vote;
import com.example.SimulacroParcial.repository.CandidateRepository;
import com.example.SimulacroParcial.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.*;

import static java.time.LocalDate.now;

@RequestMapping("/candidates")
@RestController
public class CandidateController {
    //PathVariable:objetos que se pasan por url
    //RequestBody:objeto que es pedido en el postman

    @Autowired
    private CandidateRepository candidateRepository;
    private VoteRepository voteRepository;

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

    //[GET] /votes devolver candidatos con sus respectivos votos. Es decir, contar los votos de cada candidato
    //
    //en el caso de tener dos parametros en el getmapping, se debe especificar a cada pathvariable cual es su variable con parentesis

    @GetMapping("/vote")
    private Map<Candidate,Integer> getAllVotesByCandidate(){
        Map<Candidate,Integer> candidates= new HashMap<>();

        for (Candidate c : getAll()){
            candidates.put(c,(c.getVotes()).size());
        }

        return candidates;
    }

    //Generar un Scheduler para eliminar los votos que fueron realizados hace mas de 5 minutos.


    /*para sacar lo que debo restar y comparar, debo sacar la gap que hay entre la hora que voto y la hora que es ahora*/
    @Scheduled(cron = "*/5 * * * *")
    private void deleteVotesInFiveMinutes(){
        voteRepository.deleteVotesInFiveMinutes();
    }




}
