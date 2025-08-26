package com.intela.springjwtauth.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter 
@Setter
public class Sujet {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String titre;
	    private String description;
	    @Enumerated(EnumType.STRING)
	    private SujetStatut statut;
	    
	    @ManyToOne
	    @JoinColumn(name = "professeur_id") 
	    @JsonIgnoreProperties({"id","email", "password","role", "enabled", "authorities", "username", "accountNonLocked", "credentialsNonExpired", "accountNonExpired"})
	    private User professeur;
	   
	    
	    
	    
}
