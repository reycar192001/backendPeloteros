package com.peloteros.app.peloteros.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="descuentos")
public class Descuentos implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer DescuentoID;
	
	@Column(nullable = false)
	private String Descripcion;
	
	@Column
	private Double PorcentajeDescuento;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd",iso = ISO.DATE)
	private LocalDate FechaInicio;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd",iso = ISO.DATE)
	private LocalDate FechaFin;
	
	@ManyToMany(mappedBy = "itemsDescuentos")
	private Set<Pagos> itemsPagos =new HashSet<>();
	
	public Descuentos() {
	}

	public Descuentos(Integer descuentoID, String descripcion, Double porcentajeDescuento, LocalDate fechaInicio,
			LocalDate fechaFin) {
		super();
		DescuentoID = descuentoID;
		Descripcion = descripcion;
		PorcentajeDescuento = porcentajeDescuento;
		FechaInicio = fechaInicio;
		FechaFin = fechaFin;
	}

	public Integer getDescuentoID() {
		return DescuentoID;
	}

	public void setDescuentoID(Integer descuentoID) {
		DescuentoID = descuentoID;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public Double getPorcentajeDescuento() {
		return PorcentajeDescuento;
	}

	public void setPorcentajeDescuento(Double porcentajeDescuento) {
		PorcentajeDescuento = porcentajeDescuento;
	}

	public LocalDate getFechaInicio() {
		return FechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		FechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return FechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		FechaFin = fechaFin;
	}

	public Set<Pagos> getItemsPagos() {
		return itemsPagos;
	}

	public void setItemsPagos(Set<Pagos> itemsPagos) {
		this.itemsPagos = itemsPagos;
	}

}
