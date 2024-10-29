package com.peloteros.app.peloteros.service;

import java.sql.Date;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.peloteros.app.peloteros.entity.Reservas;
import com.peloteros.app.peloteros.repository.ReservasRepository;


@Service
public class ReservasServiceImpl implements ReservasService{

	@Autowired
	private ReservasRepository repository;

	@Override
	@Transactional
	public void insert(Reservas reservas) {
		repository.save(reservas);
	}

	@Override
	@Transactional
	public void update(Reservas reservas) {
		repository.save(reservas);
	}

	@Override
	@Transactional
	public void delete(Integer ReservaID) {
		repository.deleteById(ReservaID);
	}

	@Override
	@Transactional(readOnly = true)
	public Reservas findById(Integer ReservaID) {
		return repository.findById(ReservaID).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Reservas> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Object[]> findAll_withProductos() {
		return repository.findAll_withProductos();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Collection<Reservas> findAll_withDate(Date fecha) {
		return repository.findAll_withDate(fecha);
	}

	@Override
	@Transactional(readOnly = true)
	public Reservas findReservasProductosById(Integer ReservaID) {
		return repository.findById(ReservaID).orElse(null);
	}

}
