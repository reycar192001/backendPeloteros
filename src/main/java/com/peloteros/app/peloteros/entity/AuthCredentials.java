package com.peloteros.app.peloteros.entity;

import jakarta.persistence.Column;

public class AuthCredentials {
	
	private String Correo;	
	
	private String Password;
	
	

	public String getCorreo() {
		return Correo;
	}



	public void setCorreo(String correo) {
		Correo = correo;
	}



	public String getPassword() {
		return Password;
	}



	public void setPassword(String password) {
		Password = password;
	}

	

	public AuthCredentials() {
		super();
	}



	public AuthCredentials(String correo, String password) {
		super();
		Correo = correo;
		Password = password;
	}
	
	

}
