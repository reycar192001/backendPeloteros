package com.peloteros.app.peloteros.service;

import java.util.Collection;

import com.peloteros.app.peloteros.entity.Marca;

public interface MarcaService {
	
	public abstract void insert(Marca marca);
	public abstract void update(Marca marca);
	public abstract void delete(Integer MarcaID);
	public abstract Marca findById(Integer MarcaID);
	public abstract Collection<Marca> findAll();
	public abstract int isExistNombre(String NombreMarc);
}
