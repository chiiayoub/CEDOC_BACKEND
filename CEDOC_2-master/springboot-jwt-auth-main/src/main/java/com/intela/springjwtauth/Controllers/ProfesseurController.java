package com.intela.springjwtauth.Controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intela.springjwtauth.mapper.CandidaturesMapper;
import com.intela.springjwtauth.mapper.SujetMapper;
import com.intela.springjwtauth.models.Candidature;
import com.intela.springjwtauth.models.Role;
import com.intela.springjwtauth.models.Sujet;
import com.intela.springjwtauth.models.User;
import com.intela.springjwtauth.repositories.CandidatureRepository;
import com.intela.springjwtauth.repositories.UserRepository;
import com.intela.springjwtauth.request.SujetRequest;
import com.intela.springjwtauth.services.CandidatureService;
import com.intela.springjwtauth.services.SujetService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/professeur")
@RequiredArgsConstructor
public class ProfesseurController {

	private final UserRepository userRepository;
	private final SujetService sujetService;
	@Autowired
	private  CandidatureService candidatureService;
	

@PostMapping("/proposer")
public ResponseEntity<SujetMapper> proposer(@RequestBody SujetRequest request,Principal principal){
	
	User user = userRepository.findByEmail(principal.getName())
			.orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

	   return ResponseEntity.ok(	sujetService.proposerSujet(user, request));
	   
	
}
@GetMapping("/getCandidatures")
public ResponseEntity<List<CandidaturesMapper>> getCandidatures(Principal principal){
	  User professeur = userRepository.findByEmail(principal.getName())
              .orElseThrow(() -> new RuntimeException("Professeur non trouvé"));
	  
	  if(professeur.getRole() != Role.PROFESSEUR) {
		  return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	  }
	  List<CandidaturesMapper> candidatures = candidatureService.getCandidatures(professeur);
	  
	  return ResponseEntity.ok(candidatures);
}
}
