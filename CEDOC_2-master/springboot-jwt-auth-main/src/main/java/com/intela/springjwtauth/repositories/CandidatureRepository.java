package com.intela.springjwtauth.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.intela.springjwtauth.mapper.CandidaturesMapper;
import com.intela.springjwtauth.models.Candidature;
import com.intela.springjwtauth.models.User;
@Repository
public interface CandidatureRepository extends JpaRepository<Candidature, Integer> {
	   Optional<Candidature> findByUser(User user);
	  
	   @Query("SELECT DISTINCT c FROM Candidature c"+" JOIN FETCH c.sujetsChoisis s"+" WHERE s.professeur = :professeur")
	   List<Candidature> findBySujetsProposesPar(@Param("professeur") User professeur);
	   
	   
	   
	  Candidature findById(Long id);
	  
	  
	  

	      @Query("""
	          SELECT DISTINCT c FROM Candidature c
	          JOIN FETCH c.sujetsChoisis s
	          JOIN s.professeur p
	          JOIN FETCH p.equipes e
	          WHERE e.chefEquipe.id = :chefId
	      """)
	      List<Candidature> findCandidaturesByChefId(@Param("chefId") Integer chefId);
	  
	  
	  

}
