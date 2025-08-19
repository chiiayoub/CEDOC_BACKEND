package com.intela.springjwtauth.config;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.intela.springjwtauth.models.Permission.*;
import static com.intela.springjwtauth.models.Role.ADMIN;
import static com.intela.springjwtauth.models.Role.CHEF_EQUIPE;
import static com.intela.springjwtauth.models.Role.USER;
import static com.intela.springjwtauth.models.Role.PROFESSEUR;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig{
    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize)->authorize
                        .requestMatchers("/api/v1/auth/**","/api/v1/user/**","/api/v1/CHEF_EQUIPE/**")
                        .permitAll()

                        //CHEF_EQUIPE ENDPOINTS
                        .requestMatchers("/api/v1/CHEF_EQUIPE/**").hasRole( CHEF_EQUIPE.name())
                        .requestMatchers(GET,"/api/v1/CHEF_EQUIPE/**").hasAuthority( CHEF_EQUIPE_READ.name())
                        .requestMatchers(POST,"/api/v1/CHEF_EQUIPE/**").hasAuthority( CHEF_EQUIPE_CREATE.name())
                        .requestMatchers(PUT,"/api/v1/CHEF_EQUIPE/**").hasAuthority( CHEF_EQUIPE_UPDATE.name())
                        .requestMatchers(DELETE,"/api/v1/CHEF_EQUIPE/**").hasAuthority( CHEF_EQUIPE_DELETE.name())
                       
                        
                  
                        //ADMIN ENDPOINTS
                        .requestMatchers("/api/v1/admin/**").hasRole(ADMIN.name())
                        .requestMatchers(GET,"/api/v1/admin/**").hasAuthority(ADMIN_READ.name())
                        .requestMatchers(POST,"/api/v1/admin/**").hasAuthority(ADMIN_CREATE.name())
                        .requestMatchers(PUT,"/api/v1/admin/**").hasAuthority(ADMIN_UPDATE.name())
                        .requestMatchers(DELETE,"/api/v1/admin/**").hasAuthority(ADMIN_DELETE.name())
                     
                      //Normal USER ENDPOINTS
                        .requestMatchers("/api/v1/user/**").hasRole(USER.name())
                        .requestMatchers(POST,"/api/v1/user/**").hasAuthority(USER_POSTULER.name())
                        
                        
                        //Professeur Endpoints
                        
                        .requestMatchers("/api/v1/professeur/**").hasRole(PROFESSEUR.name())
                        .requestMatchers(POST,"/api/v1/professeur/**").hasAuthority(PROFESSEUR_CREATE.name())
                        
                        .anyRequest()
                        .authenticated()
                )
                .sessionManagement((session)->session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
