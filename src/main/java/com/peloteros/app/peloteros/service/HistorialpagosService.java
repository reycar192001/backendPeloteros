package com.peloteros.app.peloteros.service;

import java.util.Collection;

import com.peloteros.app.peloteros.entity.Historialpagos;


public interface HistorialpagosService {
	
	public abstract void insert(Historialpagos historialpagos);
	public abstract void update(Historialpagos historialpagos);
	public abstract void delete(Integer HistorialPagoID);
	public abstract Historialpagos findById(Integer HistorialPagoID);
	public abstract Collection<Historialpagos> findAll();

}
