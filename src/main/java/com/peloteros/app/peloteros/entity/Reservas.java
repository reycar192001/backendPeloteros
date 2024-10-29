package com.peloteros.app.peloteros.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PostPersist;
import jakarta.persistence.Table;

@Entity
@Table(name="reservas")
public class Reservas implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ReservaID;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd",iso = ISO.DATE)
	private LocalDate FechaReserva;
	
	@Column(nullable = false)
	private String Estado;
	
	@ManyToOne
	@JsonBackReference("usuarios-reservas")
	@JoinColumn(name = "UsuarioID",nullable = false)
	private Usuarios usuariosObj;
	
	@ManyToOne
	@JsonBackReference("horarios-reservas")
	@JoinColumn(name = "HorarioID",nullable = false)
	private Horarios horariosObj;
	
	@OneToMany(mappedBy = "reservasObj")
	@JsonManagedReference("reservas-tickets")
	private Collection<Tickets> itemTickets = new ArrayList<>();
	
	@OneToMany(mappedBy = "reservasObj")
	@JsonManagedReference("reservas-pagos")
	private Collection<Pagos> itemPagos = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(name="reservas_productos",
	joinColumns=@JoinColumn(name="Reserva_ID"),
	inverseJoinColumns = @JoinColumn(name="Producto_ID"))
	private Set<Productos> itemsProductos = new HashSet<>();
	
	public Reservas() {
	}

	public Reservas(Integer reservaID, LocalDate fechaReserva, String estado) {
		super();
		ReservaID = reservaID;
		FechaReserva = fechaReserva;
		Estado = estado;
	}
	
	@PostPersist
	public void portPersist() {
		FechaReserva = LocalDate.now();
	}

	public Integer getReservaID() {
		return ReservaID;
	}

	public void setReservaID(Integer reservaID) {
		ReservaID = reservaID;
	}

	public LocalDate getFechaReserva() {
		return FechaReserva;
	}

	public void setFechaReserva(LocalDate fechaReserva) {
		FechaReserva = fechaReserva;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	public Usuarios getUsuariosObj() {
		return usuariosObj;
	}

	public void setUsuariosObj(Usuarios usuariosObj) {
		this.usuariosObj = usuariosObj;
	}

	public Horarios getHorariosObj() {
		return horariosObj;
	}

	public void setHorariosObj(Horarios horariosObj) {
		this.horariosObj = horariosObj;
	}

	public Collection<Tickets> getItemTickets() {
		return itemTickets;
	}

	public void setItemTickets(Collection<Tickets> itemTickets) {
		this.itemTickets = itemTickets;
	}

	public Set<Productos> getItemsProductos() {
		return itemsProductos;
	}

	public void setItemsProductos(Set<Productos> itemsProductos) {
		this.itemsProductos = itemsProductos;
	}

	public Collection<Pagos> getItemPagos() {
		return itemPagos;
	}

	public void setItemPagos(Collection<Pagos> itemPagos) {
		this.itemPagos = itemPagos;
	}

}
