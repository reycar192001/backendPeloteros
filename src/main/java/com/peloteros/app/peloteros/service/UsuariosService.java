package com.peloteros.app.peloteros.service;

import java.util.Collection;

import com.peloteros.app.peloteros.entity.Usuarios;

public interface UsuariosService {
	public abstract void insert(Usuarios usuarios);
	public abstract void update(Usuarios usuarios);
	public abstract void delete(Integer UsuarioID);
	public abstract Usuarios findById(Integer UsuarioID);
	public abstract Usuarios findByUsername(String Correo);
	public abstract Collection<Usuarios> findAll();
	public abstract int isExistNombre(String Nombre);
	
}
