package com.intela.springjwtauth.Controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.intela.springjwtauth.mapper.CandidaturesMapper;
import com.intela.springjwtauth.mapper.EquipeMapper;
import com.intela.springjwtauth.models.User;
import com.intela.springjwtauth.repositories.UserRepository;
import com.intela.springjwtauth.request.EquipeRequest;
import com.intela.springjwtauth.services.CandidatureService;
import com.intela.springjwtauth.services.EquipeService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
	
	private final EquipeService equipeService ;
	private final CandidatureService candidatureService;


      @PostMapping("/addEquipe")
    public ResponseEntity<EquipeMapper> creerEquipe(@RequestBody EquipeRequest request) {
        EquipeMapper equipeMapper = equipeService.creerEquipe(request);
        return ResponseEntity.ok(equipeMapper);
    }
     

@GetMapping("/getCandidatures")
public ResponseEntity<List<CandidaturesMapper>> getCandidaturesChef() {
    

    List<CandidaturesMapper> candidatures = candidatureService.getAllCandidature();
    return ResponseEntity.ok(candidatures);
}
    
    
}
