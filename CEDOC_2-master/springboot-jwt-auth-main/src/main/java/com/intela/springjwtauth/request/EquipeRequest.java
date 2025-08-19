package com.intela.springjwtauth.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class EquipeRequest {
	private String nom;
    private Integer chefEquipeId;         
    private List<Integer> professeursIds;
}
