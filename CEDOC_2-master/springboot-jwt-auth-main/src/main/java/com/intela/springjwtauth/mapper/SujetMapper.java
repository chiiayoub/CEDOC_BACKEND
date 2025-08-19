package com.intela.springjwtauth.mapper;

import com.intela.springjwtauth.models.SujetStatut;

import lombok.RequiredArgsConstructor;

public record SujetMapper(  Long id,String titre,String description,String professeurNom,String statut) {


}
