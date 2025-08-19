package com.intela.springjwtauth.Controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intela.springjwtauth.models.Sujet;
import com.intela.springjwtauth.models.User;
import com.intela.springjwtauth.repositories.UserRepository;
import com.intela.springjwtauth.request.CandidatureRequest;
import com.intela.springjwtauth.services.CandidatureService;
import com.intela.springjwtauth.services.SujetService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
	
	

private final UserRepository userRepository;

private final CandidatureService candidatureService;

private final SujetService sujetService;

@PostMapping("/postuler")
public ResponseEntity<String> postuler(@RequestBody CandidatureRequest request,Principal principal){
	
	User user = userRepository.findByEmail(principal.getName())
			.orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
	candidatureService.postuler(user, request);
	   return ResponseEntity.ok("Candidature envoyée avec succès, rôle CANDIDAT attribué");
	
}

@GetMapping("/getSujetValide")
public List<Sujet> getSujetsValides() {
    return sujetService.getSujetsValides();
}

}
