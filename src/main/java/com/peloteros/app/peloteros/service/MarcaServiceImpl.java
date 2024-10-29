package com.peloteros.app.peloteros.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.peloteros.app.peloteros.entity.Marca;
import com.peloteros.app.peloteros.repository.MarcaRepository;

@Service
public class MarcaServiceImpl implements MarcaService{

	@Autowired
	private MarcaRepository repository;
	
	@Override
	@Transactional
	public void insert(Marca marca) {
		repository.save(marca);
	}

	@Override
	@Transactional
	public void update(Marca marca) {
		repository.save(marca);
	}

	@Override
	@Transactional
	public void delete(Integer MarcaID) {
		repository.deleteById(MarcaID);
	}

	@Override
	@Transactional(readOnly =true)
	public Marca findById(Integer MarcaID) {
		return repository.findById(MarcaID).orElse(null);
	}

	@Override
	@Transactional(readOnly =true)
	public Collection<Marca> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly =true)
	public int isExistNombre(String NombreMarc) {
		return repository.isExistNombre(NombreMarc);
	}
}
