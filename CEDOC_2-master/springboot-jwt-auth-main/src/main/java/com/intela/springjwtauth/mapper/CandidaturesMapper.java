package com.intela.springjwtauth.mapper;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.intela.springjwtauth.models.Sujet;





public record CandidaturesMapper( String nom,
     String prenom,
     String statutProfessionnel, 
     String genre,
     String etatCivil,
     String nationalite,
     LocalDate dateNaissance,
     String lieuNaissance,
     String telephone,
     String email,

     String diplome,
     String specialite,
     String typeEtablissement,
     String mention,
     
     //java.util.List<String> sujetsTitres) {}
     Map<Long, String> sujets) {}










