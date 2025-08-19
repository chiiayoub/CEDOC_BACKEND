package com.intela.springjwtauth.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.intela.springjwtauth.mapper.EquipeMapper;
import com.intela.springjwtauth.models.Equipe;
import com.intela.springjwtauth.models.Role;
import com.intela.springjwtauth.models.User;
import com.intela.springjwtauth.repositories.EquipeRepository;
import com.intela.springjwtauth.repositories.UserRepository;
import com.intela.springjwtauth.request.EquipeRequest;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EquipeService {
	private final EquipeRepository equipeRepository;
	 private final UserRepository userRepository;

	 
	 public EquipeMapper mapToEquipe(Equipe equipe) {
		    String chefNom = equipe.getChefEquipe().getFirstName() + " " + equipe.getChefEquipe().getLastName();
		    List<String> profsNoms = equipe.getProfesseurs().stream()
		        .map(u -> u.getFirstName() + " " + u.getLastName())
		        .toList();

		    return new EquipeMapper(equipe.getId(), equipe.getNom(), chefNom, profsNoms);
		}
	 @Transactional
	 public EquipeMapper creerEquipe(EquipeRequest request) {
		  Equipe equipe = new Equipe();
		  equipe.setNom(request.getNom());
		  
		  User chef_equipe = userRepository.findById(request.getChefEquipeId())
				  .orElseThrow(() -> new RuntimeException("Chef d'équipe introuvable"));
		  if (chef_equipe.getRole() != Role.CHEF_EQUIPE) {
		        throw new RuntimeException("L'utilisateur n'a pas le rôle Chef d'équipe");
		    }
		  
		  equipe.setChefEquipe(chef_equipe);
		  
		  Set<User> professeurs = new HashSet<>();
	        for (Integer profId : request.getProfesseursIds()) {
	            User prof = userRepository.findById(profId)
	                .orElseThrow(() -> new RuntimeException("Professeur introuvable avec id : " + profId));
	            if (prof.getRole() != Role.PROFESSEUR) {
	                throw new RuntimeException("L'utilisateur avec id " + profId + " n'a pas le rôle Professeur");
	            }
	            professeurs.add(prof);
	        }
	        equipe.setProfesseurs(professeurs);
		  
	        Equipe saved =  equipeRepository.save(equipe);
		 
		return mapToEquipe(saved);
	 }
	 
	
}
