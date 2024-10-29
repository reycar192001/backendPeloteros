package com.peloteros.app.peloteros.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.peloteros.app.peloteros.entity.Tickets;
import com.peloteros.app.peloteros.repository.TicketsRepository;

@Service
public class TicketsServiceImpl implements TicketsService{

	@Autowired
	private TicketsRepository repository;
	
	@Override
	@Transactional
	public void insert(Tickets tickets) {
		repository.save(tickets);
	}

	@Override
	@Transactional
	public void update(Tickets tickets) {
		repository.save(tickets);
	}

	@Override
	@Transactional
	public void delete(Integer TicketID) {
		repository.deleteById(TicketID);
	}

	@Override
	@Transactional(readOnly =true)
	public Tickets findById(Integer TicketID) {
		return repository.findById(TicketID).orElse(null);
	}

	@Override
	@Transactional(readOnly =true)
	public Collection<Tickets> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public int isExistCodigo(String CodigoTicket) {
		return repository.isExistCodigo(CodigoTicket);
	}

}
