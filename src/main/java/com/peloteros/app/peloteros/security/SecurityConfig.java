package com.peloteros.app.peloteros.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable) // Deshabilita CSRF para APIs REST
            .authorizeHttpRequests(authz -> authz
            		.requestMatchers("/canchas/listar").permitAll()
                    .requestMatchers("/roles/listar").permitAll()
                    .requestMatchers("/roles/editar/*").permitAll()
                    .requestMatchers("/roles/agregar").permitAll()
                    .requestMatchers("/roles/buscar/*").permitAll()
                    .requestMatchers("/usuarios/agregar").permitAll()
                    .requestMatchers("/usuarios/buscar/*").permitAll()
                    .requestMatchers("/usuarios/listar").permitAll()
                    .requestMatchers("/usuarios/login").permitAll()
                    
                    .requestMatchers("/horarioscanchas/listar").permitAll()
                    .requestMatchers("/reservas/listar").permitAll()
                    .requestMatchers("/reservas/buscar/*").permitAll()
                    .requestMatchers("/reservas/editar/*").permitAll()
                    .requestMatchers("/reservas/agregar").permitAll()
                    .requestMatchers("/reservas/borrar/*").hasRole("CLIENTE")
                .anyRequest().authenticated()
            )
            .httpBasic() // Habilita la autenticación básica
            .and()
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // Sin estado para APIs REST

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                   .authenticationProvider(authenticationProvider())
                   .build();
    }
}