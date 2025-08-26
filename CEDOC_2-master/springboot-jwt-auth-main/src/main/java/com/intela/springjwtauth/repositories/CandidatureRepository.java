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
	  
	  
	  

	  @Query(value = "SELECT DISTINCT c.* " +
              "FROM candidature c " +
              "JOIN candidatures_sujets cs ON c.id = cs.candidature_id " +
              "JOIN sujet s ON cs.sujet_id = s.id " +
              "JOIN users p ON s.professeur_id = p.id " +
              "JOIN cedoc_2.equipe_professeurs ep ON p.id = ep.user_id " +
              "JOIN equipe e ON ep.equipe_id = e.id " +
              "WHERE e.chef_equipe_id = :chefId", 
      nativeQuery = true)
Optional<List<Candidature>> findCandidaturesForChef(@Param("chefId") Integer chefId);
	  
	  
	  @Query("SELECT DISTINCT c FROM Candidature c " +
	           "LEFT JOIN FETCH c.sujetsChoisis s")
	  Optional<List<Candidature>> findAllCandidatures();
	  
	  
	  
	  

}
