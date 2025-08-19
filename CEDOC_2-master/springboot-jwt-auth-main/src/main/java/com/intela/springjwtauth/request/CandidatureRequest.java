package com.intela.springjwtauth.request;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class CandidatureRequest {
	public String nom;
    public String prenom;
    public String statutProfessionnel;
    public String genre;
    public String etatCivil;
    public String nationalite;
    
    public LocalDate dateNaissance;
    public String lieuNaissance;
    public String telephone;
    public String diplome;
    public String specialite;
    public String typeEtablissement;
    public String mention;
    public List<Long> sujetsIds ;
}
