package com.peloteros.app.peloteros.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.peloteros.app.peloteros.entity.Pagos;
import com.peloteros.app.peloteros.repository.PagosRepository;

@Service
public class PagosServiceImpl implements PagosService{

	@Autowired
	private PagosRepository repository;

	@Override
	@Transactional
	public void insert(Pagos pagos) {
		repository.save(pagos);
	}

	@Override
	@Transactional
	public void update(Pagos pagos) {
		repository.save(pagos);
	}

	@Override
	@Transactional
	public void delete(Integer PagoID) {
		repository.deleteById(PagoID);
	}

	@Override
	@Transactional(readOnly = true)
	public Pagos findById(Integer PagoID) {
		return repository.findById(PagoID).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Pagos> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Object[]> findAll_withDescuentos() {
		return repository.findAll_withDescuentos();
	}

	@Override
	@Transactional(readOnly = true)
	public Pagos findPagosDescuentosById(Integer PagoID) {
		return repository.findById(PagoID).orElse(null);
	}
}
