package com.intela.springjwtauth;

import com.intela.springjwtauth.models.Role;


import com.intela.springjwtauth.repositories.UserRepository;
import com.intela.springjwtauth.request.RegisterRequest;
import com.intela.springjwtauth.services.AuthenticationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.intela.springjwtauth.models.Role.ADMIN;
import static com.intela.springjwtauth.models.Role.CHEF_EQUIPE;
import static com.intela.springjwtauth.models.Role.PROFESSEUR;;

@SpringBootApplication
public class SpringJwtAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringJwtAuthApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            AuthenticationService authenticationService,
            UserRepository userRepository 
    ){
        return args -> {
        	 if (userRepository.findByEmail("admin1@gmail.com").isEmpty()) {
            var admin = RegisterRequest.builder()
                    .firstName("jamal")
                    .lastName("jamal")
                    .email("admin1@gmail.com")
                    .password("1234")
                    .role(ADMIN)
                    .build();
            System.out.println("Admin token: " + authenticationService.register(admin).getToken());
        	 }
        	 if (userRepository.findByEmail("chef_equipe1@gmail.com").isEmpty()) {
            var chef_equipe = RegisterRequest.builder()
                    .firstName("KHALID")
                    .lastName("AMINI")
                    .email("chef_equipe1@gmail.com")
                    .password("1234")
                    .role(CHEF_EQUIPE)
                    .build();
            System.out.println("CHEF_EQUIPE token: " + authenticationService.register(chef_equipe).getToken());
        	 }
        	 
        	 if (userRepository.findByEmail("chef_equipe2@gmail.com").isEmpty()) {
                 var chef_equipe = RegisterRequest.builder()
                         .firstName("ahmed")
                         .lastName("hamedani")
                         .email("chef_equipe2@gmail.com")
                         .password("1234")
                         .role(CHEF_EQUIPE)
                         .build();
                 System.out.println("CHEF_EQUIPE token: " + authenticationService.register(chef_equipe).getToken());
             	 }
        	 if (userRepository.findByEmail("professeur1@gmail.com").isEmpty()) {
                 var professeuer = RegisterRequest.builder()
                         .firstName("Mohamed")
                         .lastName("hanine")
                         .email("professeur1@gmail.com")
                         .password("1234")
                         .role(PROFESSEUR)
                         .build();
                 System.out.println("Professeur token: " + authenticationService.register(professeuer).getToken());
             	 }
        	 if (userRepository.findByEmail("professeur2@gmail.com").isEmpty()) {
                 var professeuer = RegisterRequest.builder()
                         .firstName("ahmed")
                         .lastName("slimane")
                         .email("professeur2@gmail.com")
                         .password("1234")
                         .role(PROFESSEUR)
                         .build();
                 System.out.println("Professeur token: " + authenticationService.register(professeuer).getToken());
             	 }
        	 if (userRepository.findByEmail("professeur3@gmail.com").isEmpty()) {
                 var professeuer = RegisterRequest.builder()
                         .firstName("akram")
                         .lastName("akram")
                         .email("professeur3@gmail.com")
                         .password("1234")
                         .role(PROFESSEUR)
                         .build();
                 System.out.println("Professeur token: " + authenticationService.register(professeuer).getToken());
             	 }
        	 
        };
    }
}
