package com.intela.springjwtauth.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intela.springjwtauth.mapper.EquipeMapper;
import com.intela.springjwtauth.models.Equipe;
import com.intela.springjwtauth.request.EquipeRequest;
import com.intela.springjwtauth.services.EquipeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/equipes")
@RequiredArgsConstructor
public class EquipeController {
	private final EquipeService equipeService;
}
