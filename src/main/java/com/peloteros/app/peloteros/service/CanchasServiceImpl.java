package com.peloteros.app.peloteros.service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.peloteros.app.peloteros.entity.BuscarDisponibilidadModel;
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
	
	
	
	@Override
	@Transactional(readOnly = true)
	public Collection<BuscarDisponibilidadModel> findDisponibilidadByFechaAndJugadores(Date fechaConsulta, int numJugadores) {
		List<Object[]> results = repository.findDisponibilidadByFechaAndJugadores(fechaConsulta, numJugadores);
	    
	    List<BuscarDisponibilidadModel> disponibilidadList = new ArrayList<>();
	    for (Object[] row : results) {
	        BuscarDisponibilidadModel disponibilidad = new BuscarDisponibilidadModel();
	        disponibilidad.setHorarioId((Integer) row[0]);
	        disponibilidad.setHoraInicio((Time) row[1]);
	        disponibilidad.setCanchaId((Integer) row[2]);
	        disponibilidad.setNumeroCancha((Integer) row[3]);
	        disponibilidad.setPrecioHora((Double) row[4]);
	        disponibilidad.setEstado((String) row[5]);
	        disponibilidadList.add(disponibilidad);
	    }
	    
	    return disponibilidadList;
	}

}
