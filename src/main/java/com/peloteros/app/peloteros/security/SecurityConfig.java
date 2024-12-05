package com.peloteros.app.peloteros.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    
    /**
     * Constructor para inicializar SecurityConfig con el UserDetailsService.
     * 
     * @param userDetailsService El servicio de detalles del usuario para autenticación.
     */
    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    
<<<<<<< Updated upstream
   
    
=======
    /**
     * Configura los filtros de seguridad y las reglas de acceso.
     * 
     * @param http La configuración de seguridad HTTP.
     * @return El filtro de seguridad configurado.
     * @throws Exception En caso de que la configuración falle.
     */

>>>>>>> Stashed changes
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
	        .cors().and() // Habilita CORS
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/usuarios/login").permitAll()
<<<<<<< Updated upstream
                .requestMatchers("/usuarios/buscarxcorreo/**").permitAll()
                .requestMatchers("/correo/recuperarcontraseña").permitAll()
=======
>>>>>>> Stashed changes
                .requestMatchers("/api/create").permitAll()
                .requestMatchers("/api/confirmpayment").permitAll()
                .requestMatchers("/api/cancelpayment").permitAll()
                .requestMatchers("/usuarios/agregar").permitAll()
                .requestMatchers("/canchas/xfechanum/**").permitAll()                
                .requestMatchers("/horarioscanchas/**").permitAll()
                .requestMatchers("/reservas/**").permitAll()
                .anyRequest().authenticated())
            .httpBasic().disable() // Desactiva la autenticación básica
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // Sin estado

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class); // Agrega el filtro JWT

        return http.build();
    }
    
    /**
     * Crea y configura un codificador de contraseñas utilizando BCrypt.
     * 
     * @return El codificador de contraseñas.
     */

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    /**
     * Configura el proveedor de autenticación con el UserDetailsService y el codificador de contraseñas.
     * 
     * @return El proveedor de autenticación configurado.
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    
    /**
     * Configura el AuthenticationManager con el proveedor de autenticación.
     * 
     * @param http La configuración de seguridad HTTP.
     * @return El AuthenticationManager configurado.
     * @throws Exception En caso de que la configuración falle.
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                   .authenticationProvider(authenticationProvider())
                   .build();
    }
 }