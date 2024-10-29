package com.peloteros.app.peloteros.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.peloteros.app.peloteros.entity.Productos;
import com.peloteros.app.peloteros.repository.ProductosRepository;

@Service
public class ProductosServiceImpl implements ProductosService{

	@Autowired
	private ProductosRepository repository;
	
	@Override
	@Transactional
	public void insert(Productos productos) {
		repository.save(productos);
	}

	@Override
	@Transactional
	public void update(Productos productos) {
		repository.save(productos);
	}

	@Override
	@Transactional
	public void delete(Integer ProductoID) {
		repository.deleteById(ProductoID);	
	}

	@Override
	@Transactional(readOnly =true)
	public Productos findById(Integer ProductoID) {
		return repository.findById(ProductoID).orElse(null);
	}

	@Override
	@Transactional(readOnly =true)
	public Collection<Productos> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly =true)
	public int isExistNombre(String NombreProd) {
		return repository.isExistNombre(NombreProd);
	}

}
