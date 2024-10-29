package com.peloteros.app.peloteros.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.peloteros.app.peloteros.entity.Canchas;
import com.peloteros.app.peloteros.repository.CanchasRepository;

@Service
public class CanchasServiceImpl implements CanchasService{

	@Autowired
	private CanchasRepository repository;
	
	@Override
	@Transactional
	public void insert(Canchas canchas) {
		repository.save(canchas);
	}

	@Override
	@Transactional
	public void update(Canchas canchas) {
		repository.save(canchas);
	}

	@Override
	@Transactional
	public void delete(Integer CanchaID) {
		repository.deleteById(CanchaID);
	}

	@Override
	@Transactional(readOnly = true)
	public Canchas findById(Integer CanchaID) {
		return repository.findById(CanchaID).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Canchas> findAll() {
		return repository.findAll();
	}

}
