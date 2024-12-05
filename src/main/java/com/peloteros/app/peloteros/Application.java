package com.peloteros.app.peloteros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}	

	@Bean
	public WebMvcConfigurer corsConfigurer() {
	    return new WebMvcConfigurer() {
	        @Override
	        public void addCorsMappings(CorsRegistry registry) {                              
	            registry.addMapping("/**") // Permitir todos los endpoints
	                    .allowedOrigins("*") // Permitir todos los orígenes
	                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos permitidos
	                    .allowedHeaders("*") // Permitir todos los headers
	                    .exposedHeaders("Authorization"); // Exponer el header de autorización
	        }
	    };
	}
}
