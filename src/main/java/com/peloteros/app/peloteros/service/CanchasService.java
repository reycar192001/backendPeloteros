package com.peloteros.app.peloteros.service;

import java.util.Collection;

import com.peloteros.app.peloteros.entity.Canchas;

public interface CanchasService {

	public abstract void insert(Canchas canchas);
	public abstract void update(Canchas canchas);
	public abstract void delete(Integer CanchaID);
	public abstract Canchas findById(Integer CanchaID);
	public abstract Collection<Canchas> findAll();
}
