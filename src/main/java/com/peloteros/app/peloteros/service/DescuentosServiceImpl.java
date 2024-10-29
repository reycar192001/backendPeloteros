package com.peloteros.app.peloteros.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.peloteros.app.peloteros.entity.Descuentos;
import com.peloteros.app.peloteros.repository.DescuentosRepository;

@Service
public class DescuentosServiceImpl implements DescuentosService{

	@Autowired
	private DescuentosRepository repository;
	
	@Override
	@Transactional
	public void insert(Descuentos descuentos) {
		repository.save(descuentos);
	}

	@Override
	@Transactional
	public void update(Descuentos descuentos) {
		repository.save(descuentos);
	}

	@Override
	@Transactional
	public void delete(Integer DescuentoID) {
		repository.deleteById(DescuentoID);
	}

	@Override
	@Transactional(readOnly = true)
	public Descuentos findById(Integer DescuentoID) {
		return repository.findById(DescuentoID).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Descuentos> findAll() {
		return repository.findAll();
	}

}
