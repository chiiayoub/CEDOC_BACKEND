package com.intela.springjwtauth.repositories;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.intela.springjwtauth.models.Candidature;
import com.intela.springjwtauth.models.Sujet;
import com.intela.springjwtauth.models.SujetStatut;
import com.intela.springjwtauth.models.User;
@Repository
public interface SujetRepository extends JpaRepository<Sujet, Long>{
	List<Sujet> findByStatut(SujetStatut statut);
	@Query("SELECT s FROM Sujet s WHERE s.professeur IN :professeurs")
    List<Sujet> findAllByProfesseurIn(@Param("professeurs") Set<User> professeurs);
	  
}
