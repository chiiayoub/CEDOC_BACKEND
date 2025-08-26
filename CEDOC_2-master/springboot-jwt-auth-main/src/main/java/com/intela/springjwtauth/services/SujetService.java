package com.intela.springjwtauth.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.intela.springjwtauth.mapper.SujetMapper;
import com.intela.springjwtauth.mapper.SujetMapperChef;
import com.intela.springjwtauth.models.Equipe;
import com.intela.springjwtauth.models.Sujet;
import com.intela.springjwtauth.models.SujetStatut;
import com.intela.springjwtauth.models.User;
import com.intela.springjwtauth.repositories.EquipeRepository;
import com.intela.springjwtauth.repositories.SujetRepository;
import com.intela.springjwtauth.repositories.UserRepository;
import com.intela.springjwtauth.request.SujetRequest;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SujetService {
private final SujetRepository sujetRepository;
private final UserRepository userRepository;
private final EquipeRepository equipeRepository;


public SujetMapper mapToSujetMapper(Sujet sujet) {
	User user =sujet.getProfesseur();
	
	
	
    return new SujetMapper(
        sujet.getId(),
        sujet.getTitre(),
        sujet.getDescription(),
        sujet.getProfesseur().toString(),
        
        sujet.getStatut().name()
    );
}
public SujetMapperChef mapToSujetMapper_chef(Sujet sujet) {
	User user =sujet.getProfesseur();
	var equipe = equipeRepository.findByChefEquipe(user)
    		.orElseThrow(() -> new RuntimeException("Aucune équipe trouvée pour ce chef"));
	
	
    return new SujetMapperChef(
        sujet.getId(),
        sujet.getTitre(),
        sujet.getDescription(),
        sujet.getProfesseur().toString(),
        equipe.getId(),
        sujet.getStatut().name()
    );
}


public SujetMapper proposerSujet(User user,SujetRequest request ) {
	Sujet sujet=new Sujet();
    sujet.setProfesseur(user);
    sujet.setTitre(request.titre);
    sujet.setDescription(request.description);
    sujet.setStatut(SujetStatut.EN_ATTENTE);
   
    		

    Sujet saved = sujetRepository.save(sujet);
    
    return mapToSujetMapper(saved);
}

@Transactional
public SujetMapper validerSujet(Long sujetId) {
    // 1️⃣ Récupération de l'utilisateur connecté (chef d'équipe qui valide)
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    String emailChef;
    if (principal instanceof UserDetails) {
        emailChef = ((UserDetails) principal).getUsername();
    } else {
        emailChef = principal.toString();
    }

    User chefEquipe = userRepository.findByEmail(emailChef)
        .orElseThrow(() -> new RuntimeException("Chef d'équipe introuvable"));

    // 2️⃣ Récupération du sujet à valider
    Sujet sujet = sujetRepository.findById(sujetId)
        .orElseThrow(() -> new RuntimeException("Sujet introuvable"));

    // 3️⃣ Récupération du professeur qui a proposé ce sujet
    User professeur = sujet.getProfesseur();
    if (professeur == null) {
        throw new RuntimeException("Aucun professeur lié à ce sujet");
    }

    // 4️⃣ Récupération de l'équipe du professeur
    Equipe equipeProfesseur = equipeRepository.findByProfesseursContaining(professeur)
        .orElseThrow(() -> new RuntimeException("Aucune équipe trouvée pour ce professeur"));

    // 5️⃣ Validation du sujet
    sujet.setStatut(SujetStatut.VALIDE);
    Sujet savedSujet = sujetRepository.save(sujet);

    // 6️⃣ Ajout du sujet à l'équipe du professeur
    equipeProfesseur.getSujetsValidés().add(savedSujet);
    equipeRepository.save(equipeProfesseur);

    return mapToSujetMapper(savedSujet);
}


public SujetMapper refuserSujet(Long sujetId) {
    Sujet sujet = sujetRepository.findById(sujetId)
            .orElseThrow(() -> new RuntimeException("Sujet introuvable"));
    sujet.setStatut(SujetStatut.REFUSE);
 Sujet saved = sujetRepository.save(sujet);
    
    return mapToSujetMapper(saved);
}
public List<Sujet> getSujetsValides() {
    return sujetRepository.findByStatut(SujetStatut.VALIDE);
}
public List<Sujet> getSujetsChefEquipe() {
  
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    String emailChef;
    if (principal instanceof UserDetails) {
        emailChef = ((UserDetails) principal).getUsername();  //  l'email
    } else {
        emailChef = principal.toString();
    }

    // Récupérer le User chef d'équipe à partir de l'email
    User chefEquipe = userRepository.findByEmail(emailChef)
        .orElseThrow(() -> new RuntimeException("Chef d'équipe introuvable"));

    List<Equipe> equipes = equipeRepository.findAllByChefEquipeWithProfesseurs(chefEquipe);

    if (equipes.isEmpty()) {
        throw new RuntimeException("Equipe introuvable pour ce chef");
    }

    
    // Par exemple, récupérer tous les professeurs de toutes les équipes du chef
    Set<User> professeurs = equipes.stream()
        .flatMap(equipe -> equipe.getProfesseurs().stream())
        .collect(Collectors.toSet());

    // Ensuite récupérer les sujets proposés par ces professeurs
    return sujetRepository.findAllByProfesseurIn(professeurs);

}

}