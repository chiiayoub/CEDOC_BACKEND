package com.intela.springjwtauth.repositories;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.intela.springjwtauth.models.Equipe;
import com.intela.springjwtauth.models.Sujet;
import com.intela.springjwtauth.models.User;

@Repository
public interface EquipeRepository extends JpaRepository<Equipe, Long>  {
	Optional<Equipe> findByProfesseursContaining(User professeur);

	@Query("SELECT e FROM Equipe e LEFT JOIN FETCH e.professeurs WHERE e.chefEquipe = :chefEquipe")
	List<Equipe> findAllByChefEquipeWithProfesseurs(@Param("chefEquipe") User chefEquipe);
	
	Optional<Equipe> findByChefEquipe(User user);
	

}
