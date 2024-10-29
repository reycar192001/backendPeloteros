package com.peloteros.app.peloteros.service;

import java.util.Collection;

import com.peloteros.app.peloteros.entity.Categoria;

public interface CategoriaService {
	
	public abstract void insert(Categoria categoria);
	public abstract void update(Categoria categoria);
	public abstract void delete(Integer CategoriaID);
	public abstract Categoria findById(Integer CategoriaID);
	public abstract Collection<Categoria> findAll();
	public abstract int isExistNombre(String NombreCat);
}
