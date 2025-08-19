package com.intela.springjwtauth.models;

import lombok.Getter;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.intela.springjwtauth.models.Permission.*;

@RequiredArgsConstructor
public enum Role {
	   USER(
	            Set.of(
	            	     Permission.USER_POSTULER
	            		)
	                      
	    ),
    CHEF_EQUIPE(
            Set.of(
            		CHEF_EQUIPE_CREATE,
                    CHEF_EQUIPE_READ,
                    CHEF_EQUIPE_DELETE,
                    CHEF_EQUIPE_UPDATE
            )
    ),
    ADMIN(
            Set.of(
                    ADMIN_CREATE,
                    ADMIN_READ,
                    ADMIN_DELETE,
                    ADMIN_UPDATE,
                    CHEF_EQUIPE_CREATE,
                    CHEF_EQUIPE_READ,
                    CHEF_EQUIPE_DELETE,
                    CHEF_EQUIPE_UPDATE
            )
    ), CANDIDAT(
            Set.of( )
    ),
    PROFESSEUR(
    		Set.of(
    				
    				PROFESSEUR_CREATE,
    				PROFESSEUR_READ,
    				PROFESSEUR_DELETE,
    				PROFESSEUR_UPDATE
    				)
    		);

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
