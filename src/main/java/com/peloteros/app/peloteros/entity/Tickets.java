package com.peloteros.app.peloteros.entity;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PostPersist;
import jakarta.persistence.Table;

@Entity
@Table(name="tickets")
public class Tickets implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer TicketID;
	
	@Column(nullable = false)
	private String CodigoTicket;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd",iso = ISO.DATE)
	private LocalDate FechaGeneracion;
	
	@ManyToOne
	@JsonBackReference("reservas-tickets")
	@JoinColumn(name = "ReservaID",nullable = false)
	private Reservas reservasObj;
	
	public Tickets() {
	}
	
	public Tickets(Integer ticketID, String codigoTicket, LocalDate fechaGeneracion) {
		super();
		TicketID = ticketID;
		CodigoTicket = codigoTicket;
		FechaGeneracion = fechaGeneracion;
	}

	@PostPersist
	public void portPersist() {
		FechaGeneracion = LocalDate.now();
	}

	public Integer getTicketID() {
		return TicketID;
	}

	public void setTicketID(Integer ticketID) {
		TicketID = ticketID;
	}

	public String getCodigoTicket() {
		return CodigoTicket;
	}

	public void setCodigoTicket(String codigoTicket) {
		CodigoTicket = codigoTicket;
	}

	public LocalDate getFechaGeneracion() {
		return FechaGeneracion;
	}

	public void setFechaGeneracion(LocalDate fechaGeneracion) {
		FechaGeneracion = fechaGeneracion;
	}

	public Reservas getReservasObj() {
		return reservasObj;
	}

	public void setReservasObj(Reservas reservasObj) {
		this.reservasObj = reservasObj;
	}
}
