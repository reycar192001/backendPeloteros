package com.peloteros.app.peloteros.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.peloteros.app.peloteros.entity.Pagos;


@Service
public interface PagosService {
	
	public abstract void insert(Pagos pagos);
	public abstract void update(Pagos pagos);
	public abstract void delete(Integer PagoID);
	public abstract Pagos findById(Integer PagoID);
	public abstract Collection<Pagos> findAll();
	
	public abstract Collection<Object[]> findAll_withDescuentos();
	public abstract Pagos findPagosDescuentosById(Integer PagoID);
}
