package com.intela.springjwtauth.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    
    CHEF_EQUIPE_READ("CHEF_EQUIPE:read"),
    CHEF_EQUIPE_UPDATE("CHEF_EQUIPE:update"),
    CHEF_EQUIPE_CREATE("CHEF_EQUIPE:create"),
    CHEF_EQUIPE_DELETE("CHEF_EQUIPE:delete"),
    
    USER_POSTULER("user:postuler"),
    PROFESSEUR_CREATE("professeur::create"),
	PROFESSEUR_READ("professeur::read"),
	PROFESSEUR_DELETE("professeur::delete"),
	PROFESSEUR_UPDATE("professeur::update");
   
	 

    @Getter
    private final String permission;

}
