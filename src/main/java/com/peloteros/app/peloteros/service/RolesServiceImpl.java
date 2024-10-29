package com.peloteros.app.peloteros.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.peloteros.app.peloteros.entity.Roles;
import com.peloteros.app.peloteros.repository.RolesRepository;


@Service
public class RolesServiceImpl implements RolesService {
	
	@Autowired
	private RolesRepository repository;

	@Override
	@Transactional
	public void insert(Roles roles) {
		repository.save(roles);
	}

	@Override
	@Transactional
	public void update(Roles roles) {
		repository.save(roles);
	}

	@Override
	@Transactional
	public void delete(Integer RoleID) {
		repository.deleteById(RoleID);	
	}

	@Override
	@Transactional(readOnly =true)
	public Roles findById(Integer RoleID) {
		return repository.findById(RoleID).orElse(null);
	}

	@Override
	@Transactional(readOnly =true)
	public Collection<Roles> findAll() {
		return repository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public int isExistNombre(String Nombre) {
		return repository.isExistNombre(Nombre);
	}
}
