package com.peloteros.app.peloteros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.peloteros.app.peloteros.entity.Tickets;

public interface TicketsRepository extends JpaRepository<Tickets, Integer>  {
	
	@Query(value="select count(*) from tickets where CodigoTicket=?",nativeQuery = true)
	public abstract int isExistCodigo(String CodigoTicket);

}
