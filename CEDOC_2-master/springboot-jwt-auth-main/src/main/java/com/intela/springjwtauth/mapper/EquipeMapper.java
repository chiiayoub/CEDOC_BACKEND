package com.intela.springjwtauth.mapper;

import java.util.List;

public record EquipeMapper(Long id,String nom,String chefEquipeNom,List<String> professeursNoms) {

}
