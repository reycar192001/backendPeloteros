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
import jakarta.persistence.Table;

@Entity
@Table(name="pagos")
public class Pagos implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer PagoID;
	
	@Column(nullable = false)
	private Double Monto;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd",iso = ISO.DATE)
	private LocalDate FechaPago;
	
	@Column(nullable = false)
	private String MetodoPago;
	
	@ManyToOne
	@JsonBackReference("reservas-pagos")
	@JoinColumn(name = "ReservaID",nullable = false)
	private Reservas reservasObj;
	
	@ManyToMany
	@JoinTable(name="pagos_descuentos",
	joinColumns=@JoinColumn(name="Pago_ID"),
	inverseJoinColumns = @JoinColumn(name="Descuento_ID"))
	private Set<Descuentos> itemsDescuentos = new HashSet<>();
	
	@OneToMany(mappedBy = "pagosObj")
	@JsonManagedReference("pagos-historial")
	private Collection<Historialpagos> itemHistorial = new ArrayList<>();
	
	public Pagos() {
	}

	public Pagos(Integer pagoID, Double monto, LocalDate fechaPago, String metodoPago) {
		super();
		PagoID = pagoID;
		Monto = monto;
		FechaPago = fechaPago;
		MetodoPago = metodoPago;
	}

	public Integer getPagoID() {
		return PagoID;
	}

	public void setPagoID(Integer pagoID) {
		PagoID = pagoID;
	}

	public Double getMonto() {
		return Monto;
	}

	public void setMonto(Double monto) {
		Monto = monto;
	}

	public LocalDate getFechaPago() {
		return FechaPago;
	}

	public void setFechaPago(LocalDate fechaPago) {
		FechaPago = fechaPago;
	}

	public String getMetodoPago() {
		return MetodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		MetodoPago = metodoPago;
	}

	public Reservas getReservasObj() {
		return reservasObj;
	}

	public void setReservasObj(Reservas reservasObj) {
		this.reservasObj = reservasObj;
	}

	public Set<Descuentos> getItemsDescuentos() {
		return itemsDescuentos;
	}

	public void setItemsDescuentos(Set<Descuentos> itemsDescuentos) {
		this.itemsDescuentos = itemsDescuentos;
	}

	public Collection<Historialpagos> getItemHistorial() {
		return itemHistorial;
	}

	public void setItemHistorial(Collection<Historialpagos> itemHistorial) {
		this.itemHistorial = itemHistorial;
	}
	
}
