package com.intela.springjwtauth.services;


import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intela.springjwtauth.mapper.CandidaturesMapper;
import com.intela.springjwtauth.mapper.SujetMapper;
import com.intela.springjwtauth.models.Candidature;
import com.intela.springjwtauth.models.CandidatureStatus;
import com.intela.springjwtauth.models.Equipe;
import com.intela.springjwtauth.models.Role;
import com.intela.springjwtauth.models.Sujet;
import com.intela.springjwtauth.models.SujetStatut;
import com.intela.springjwtauth.models.User;
import com.intela.springjwtauth.repositories.CandidatureRepository;
import com.intela.springjwtauth.repositories.EquipeRepository;
import com.intela.springjwtauth.repositories.SujetRepository;
import com.intela.springjwtauth.repositories.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CandidatureService {

private final CandidatureRepository candidatureRepository;



private final UserRepository userRepository ;
private final SujetRepository sujetRepository ;
private final SujetService sujetService;
public final EquipeRepository equipeRepository;

public CandidaturesMapper mapToCandidatures(Candidature candidature,User professeur) {
	  List<Sujet> sujetsDuProf = candidature.getSujetsChoisis().stream()
	            .filter(s -> s.getProfesseur().getId().equals(professeur.getId()))
	            .toList();
	
	  Map<Long, String> sujetsTitres1 = new HashMap<Long, String>();
	  for (Sujet sujet : sujetsDuProf) {
		  sujetsTitres1.put(sujet.getId(), sujet.getTitre());
	}
	
	return new CandidaturesMapper(candidature.getNom(), candidature.getPrenom(),candidature.getStatutProfessionnel() , candidature.getGenre(), candidature.getEtatCivil(),
			candidature.getNationalite(),candidature.getDateNaissance(),candidature.getLieuNaissance() ,  candidature.getTelephone(),candidature.getEmail(), candidature.getDiplome(), candidature.getSpecialite()
			,candidature.getTypeEtablissement(), candidature.getMention(),sujetsTitres1);
	}
////////////////////////////////////////////////////Admin Candidatures

public CandidaturesMapper mapToCandidaturesAdmin(Candidature candidature) {
	  List<Sujet> sujets = candidature.getSujetsChoisis();
	   List<SujetMapper> sujetsMappers = new ArrayList<SujetMapper>();
	   for (Sujet sujet : sujets) {
		sujetsMappers.add(sujetService.mapToSujetMapper(sujet));
	}
	   
	  Map<Long, String> sujetsTitres1 = new HashMap<Long, String>();
	  for (SujetMapper sujetMapper : sujetsMappers) {
		  sujetsTitres1.put(sujetMapper.id(), sujetMapper.titre());
	}
	
	return new CandidaturesMapper(candidature.getNom(), candidature.getPrenom(),candidature.getStatutProfessionnel() , candidature.getGenre(), candidature.getEtatCivil(),
			candidature.getNationalite(),candidature.getDateNaissance(),candidature.getLieuNaissance() ,  candidature.getTelephone(),candidature.getEmail(), candidature.getDiplome(), candidature.getSpecialite()
			,candidature.getTypeEtablissement(), candidature.getMention(),sujetsTitres1);
	}


/*
public List<CandidaturesMapper> getCandidaturesAdmin() {
     List<Candidature> candidatures = candidatureRepository.findAll();
     return candidatures.stream()
             .map(c -> mapToCandidaturesAdmin(c))
             .toList();
*/

////////////////////////////////////////////////////////////////////////



public Candidature postuler(User user , com.intela.springjwtauth.request.CandidatureRequest request) {
    Candidature candidature = candidatureRepository.findByUser(user).orElse(new Candidature());
	 
	candidature.setNom(request.nom);
    candidature.setPrenom(request.prenom);
    candidature.setStatutProfessionnel(request.statutProfessionnel);
    candidature.setGenre(request.genre);
    candidature.setEtatCivil(request.etatCivil);
    candidature.setNationalite(request.nationalite);
    candidature.setDateNaissance(request.dateNaissance);
    candidature.setLieuNaissance(request.lieuNaissance);
    candidature.setTelephone(request.telephone);
    candidature.setDiplome(request.diplome);
    candidature.setSpecialite(request.specialite);
    candidature.setTypeEtablissement(request.typeEtablissement);
    candidature.setMention(request.mention);
    candidature.setEmail(user.getEmail());
    candidature.setUser(user);
    
    if(request.sujetsIds.size()!=3)
    	throw new RuntimeException("choisissez 3 sujet!");
    
    List<Sujet> sujets = sujetRepository.findAllById(request.sujetsIds);
    if (sujets.size() != request.sujetsIds.size()) {
        throw new RuntimeException("Certains sujets choisis sont introuvables.");
    }
    

    candidature.setSujetsChoisis(sujets);
   
    candidatureRepository.save(candidature);
    
    user.setRole(Role.CANDIDAT);
    userRepository.save(user);
    
    return candidature;
    
	
    
	
}

public List<CandidaturesMapper> getCandidatures(User prof) {
     List<Candidature> candidatures = candidatureRepository.findBySujetsProposesPar(prof);
     return candidatures.stream()
             .map(c -> mapToCandidatures(c,prof))
             .toList();
}

@Transactional
    public List<CandidaturesMapper> getCandidaturesByChefId(Integer chefId) {
        List<Candidature> candidatures = candidatureRepository.findCandidaturesByChefId(chefId);

        return candidatures.stream()
            .map(candidature -> new CandidaturesMapper(
                candidature.getNom(),
                candidature.getPrenom(),
                candidature.getStatutProfessionnel(),
                candidature.getGenre(),
                candidature.getEtatCivil(),
                candidature.getNationalite(),
                candidature.getDateNaissance(),
                candidature.getLieuNaissance(),
                candidature.getTelephone(),
                candidature.getEmail(),
                candidature.getDiplome(),
                candidature.getSpecialite(),
                candidature.getTypeEtablissement(),
                candidature.getMention(),
                candidature.getSujetsChoisis().stream()
                    // on garde uniquement les sujets liés aux équipes de ce chef
                    .filter(s -> s.getProfesseur().getEquipes().stream()
                        .anyMatch(equipe -> equipe.getChefEquipe().getId().equals(chefId)))
                    .collect(Collectors.toMap(
                        Sujet::getId,
                        Sujet::getTitre,
                        (oldValue, newValue) -> oldValue,
                        HashMap::new
                    ))
            ))
            .toList();
    }



public void accepterCandidature(Long id) {
	 Candidature candidature = candidatureRepository.findById(id);
	 candidature.setStatus(CandidatureStatus.ACCEPTEE);
	  candidatureRepository.save(candidature);
	 
}
   


}
