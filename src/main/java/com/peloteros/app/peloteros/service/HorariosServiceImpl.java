package com.peloteros.app.peloteros.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.peloteros.app.peloteros.entity.Horarios;
import com.peloteros.app.peloteros.repository.HorariosRepository;

@Service
public class HorariosServiceImpl implements HorariosService{

	@Autowired
	private HorariosRepository repository;
	
	@Override
	@Transactional
	public void insert(Horarios horarios) {
		repository.save(horarios);
	}

	@Override
	@Transactional
	public void update(Horarios horarios) {
		repository.save(horarios);
	}

	@Override
	@Transactional
	public void delete(Integer HorarioID) {
		repository.deleteById(HorarioID);
	}

	@Override
	@Transactional(readOnly = true)
	public Horarios findById(Integer HorarioID) {
		return repository.findById(HorarioID).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Horarios> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Object[]> findAll_withCanchas() {
		return repository.findAll_withCanchas();
	}

	@Override
	@Transactional(readOnly = true)
	public Horarios findHorariosCanchasById(Integer HorarioID) {
		return repository.findById(HorarioID).orElse(null);
	}

}
