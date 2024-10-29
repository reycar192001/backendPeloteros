package com.peloteros.app.peloteros.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.peloteros.app.peloteros.entity.Historialpagos;
import com.peloteros.app.peloteros.repository.HistorialpagosRepository;

@Service
public class HistorialpagosServiceImpl implements HistorialpagosService{

	@Autowired
	private HistorialpagosRepository repository;
	
	@Override
	@Transactional
	public void insert(Historialpagos historialpagos) {
		repository.save(historialpagos);	
	}

	@Override
	@Transactional
	public void update(Historialpagos historialpagos) {
		repository.save(historialpagos);
	}

	@Override
	@Transactional
	public void delete(Integer HistorialPagoID) {
		repository.deleteById(HistorialPagoID);
	}

	@Override
	@Transactional(readOnly = true)
	public Historialpagos findById(Integer HistorialPagoID) {
		return repository.findById(HistorialPagoID).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Historialpagos> findAll() {
		return repository.findAll();
	}

}
