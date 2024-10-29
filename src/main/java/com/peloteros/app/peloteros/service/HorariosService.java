package com.peloteros.app.peloteros.service;

import java.util.Collection;

import com.peloteros.app.peloteros.entity.Horarios;

public interface HorariosService {
	
	public abstract void insert(Horarios horarios);
	public abstract void update(Horarios horarios);
	public abstract void delete(Integer HorarioID);
	public abstract Horarios findById(Integer HorarioID);
	public abstract Collection<Horarios> findAll();
	
	public abstract Collection<Object[]> findAll_withCanchas();
	public abstract Horarios findHorariosCanchasById(Integer HorarioID);
}
