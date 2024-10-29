package com.peloteros.app.peloteros.service;

import java.sql.Date;
import java.util.Collection;

import org.springframework.stereotype.Service;

import com.peloteros.app.peloteros.entity.Reservas;

@Service
public interface ReservasService {
	
	public abstract void insert(Reservas reservas);
	public abstract void update(Reservas reservas);
	public abstract void delete(Integer ReservaID);
	public abstract Reservas findById(Integer ReservaID);
	public abstract Collection<Reservas> findAll();
	
	public abstract Collection<Object[]> findAll_withProductos();
	public abstract Collection<Reservas> findAll_withDate(Date fecha);
	public abstract Reservas findReservasProductosById(Integer ReservaID);

}
