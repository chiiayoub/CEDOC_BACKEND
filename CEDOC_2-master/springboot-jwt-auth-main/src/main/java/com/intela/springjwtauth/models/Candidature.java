package com.intela.springjwtauth.models;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;



import jakarta.persistence.*;
import lombok.*;
@Getter
@Setter
@Entity
public class Candidature {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	
	private String nom;
    private String prenom;
    private String statutProfessionnel; // Salarié / Non salarié
    private String genre; // F ou M
    private String etatCivil; // Marié / Célibataire
    private String nationalite;
    private LocalDate dateNaissance;
    private String lieuNaissance;
    private String telephone;
    private String email;

    private String diplome; // Ingénieur / Master
    private String specialite;
    private String typeEtablissement;
    private String mention;
    
    @Enumerated(EnumType.STRING)
    private CandidatureStatus status;
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
     
    @ManyToMany
    @JoinTable(
    		name="candidatures_sujets",
    		joinColumns=@JoinColumn(name="candidature_id"),
    		inverseJoinColumns =@JoinColumn(name="sujet_id")
    		)
   
            private List<Sujet> sujetsChoisis =new ArrayList<>();
}