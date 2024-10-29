package com.peloteros.app.peloteros.service;

import java.util.Collection;

import com.peloteros.app.peloteros.entity.Productos;

public interface ProductosService {
	
	public abstract void insert(Productos productos);
	public abstract void update(Productos productos);
	public abstract void delete(Integer ProductoID);
	public abstract Productos findById(Integer ProductoID);
	public abstract Collection<Productos> findAll();
	public abstract int isExistNombre(String NombreProd);
}
