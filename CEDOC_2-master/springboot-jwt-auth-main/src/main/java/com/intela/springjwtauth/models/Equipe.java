package com.intela.springjwtauth.models;


import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Equipe {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String nom;
	    
	    @ManyToOne()
	    @JoinColumn(name = "chef_equipe_id")
	    private User chefEquipe;
	    
	    @ManyToMany
	    @JoinTable(
	        name = "equipe_professeurs",
	        joinColumns = @JoinColumn(name = "equipe_id"),
	        inverseJoinColumns = @JoinColumn(name = "user_id")
	    )
	    private Set<User> professeurs;
	    
	    @ManyToMany
	    @JoinTable(
	        name = "equipe_sujets_valides",
	        joinColumns = @JoinColumn(name = "equipe_id"),
	        inverseJoinColumns = @JoinColumn(name = "sujet_id")
	    )
	    private Set<Sujet> sujetsValidés = new HashSet<>();

}
