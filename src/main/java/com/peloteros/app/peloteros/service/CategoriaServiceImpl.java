package com.peloteros.app.peloteros.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.peloteros.app.peloteros.entity.Categoria;
import com.peloteros.app.peloteros.repository.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService{

	@Autowired
	private CategoriaRepository repository;
	
	@Override
	@Transactional
	public void insert(Categoria categoria) {
		repository.save(categoria);
	}

	@Override
	@Transactional
	public void update(Categoria categoria) {
		repository.save(categoria);
	}

	@Override
	@Transactional
	public void delete(Integer CategoriaID) {
		repository.deleteById(CategoriaID);	
	}

	@Override
	@Transactional(readOnly =true)
	public Categoria findById(Integer CategoriaID) {
		return repository.findById(CategoriaID).orElse(null);
	}

	@Override
	@Transactional(readOnly =true)
	public Collection<Categoria> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly =true)
	public int isExistNombre(String NombreCat) {
		return repository.isExistNombre(NombreCat);
	}

}
