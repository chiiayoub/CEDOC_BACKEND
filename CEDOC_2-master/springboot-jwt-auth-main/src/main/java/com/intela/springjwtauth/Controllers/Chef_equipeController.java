package com.intela.springjwtauth.Controllers;

import java.security.Principal;

import java.util.List;


import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.intela.springjwtauth.mapper.CandidaturesMapper;
import com.intela.springjwtauth.mapper.SujetMapper;

import com.intela.springjwtauth.models.Sujet;
import com.intela.springjwtauth.models.User;
import com.intela.springjwtauth.repositories.UserRepository;
import com.intela.springjwtauth.services.CandidatureService;
import com.intela.springjwtauth.services.SujetService;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/CHEF_EQUIPE")
public class Chef_equipeController {
	
private final SujetService sujetService;
private final CandidatureService candidatureService;
private final UserRepository userRepository;

    @PostMapping("/post")
    public ResponseEntity<String> CHEF_EQUIPE_Post(){
        return ResponseEntity.ok("Manager:: POST");
    }

    @PutMapping ("/put")
    public ResponseEntity<String> CHEF_EQUIPE_Put(){
        return ResponseEntity.ok("CHEF_EQUIPE:: PUT");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> CHEF_EQUIPE__Delete(){
        return ResponseEntity.ok("CHEF_EQUIPE:: DELETE");
    }

    @GetMapping("/get")
    public ResponseEntity<String> CHEF_EQUIPE_Get(){
        return ResponseEntity.ok("CHEF_EQUIPE:: GET");
    }

@PutMapping("/validerSujet/{id}") 
public SujetMapper validerSujet(@PathVariable Long id) {
    return sujetService.validerSujet(id);
}


@PutMapping("/refuserSujet/{id}")
public SujetMapper refuserSujet(@PathVariable Long id) {
    return sujetService.refuserSujet(id);
}
@GetMapping("/getSujetsEquipe")
public List<Sujet> getSujetsChefEquipe() {
    return sujetService.getSujetsChefEquipe();
}
/*
@PutMapping("/acceptercandidature")
public ResponseEntity<String> accepterCandidature(@PathVariable Long id){
	candidatureService.accepterCandidature(id);
	return ResponseEntity.ok("Candidature accepted");
} 
*/

@GetMapping("/getCandidatures")
public ResponseEntity<List<CandidaturesMapper>> getCandidaturesChef(Principal principal) {
    User chef = userRepository.findByEmail(principal.getName())
            .orElseThrow(() -> new RuntimeException("Chef non trouvé"));

    List<CandidaturesMapper> candidatures = candidatureService.getCandidaturesByChefId(chef.getId());
    return ResponseEntity.ok(candidatures);
}


}
