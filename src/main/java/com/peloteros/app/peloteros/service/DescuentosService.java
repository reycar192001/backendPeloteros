package com.peloteros.app.peloteros.service;

import java.util.Collection;

import com.peloteros.app.peloteros.entity.Descuentos;

public interface DescuentosService {
	
	public abstract void insert(Descuentos descuentos);
	public abstract void update(Descuentos descuentos);
	public abstract void delete(Integer DescuentoID);
	public abstract Descuentos findById(Integer DescuentoID);
	public abstract Collection<Descuentos> findAll();

}
