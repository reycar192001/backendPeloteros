package com.peloteros.app.peloteros.service;

import java.util.Collection;

import com.peloteros.app.peloteros.entity.Tickets;


public interface TicketsService {
	
	public abstract void insert(Tickets tickets);
	public abstract void update(Tickets tickets);
	public abstract void delete(Integer TicketID);
	public abstract Tickets findById(Integer TicketID);
	public abstract Collection<Tickets> findAll();
	public abstract int isExistCodigo(String CodigoTicket);

}
