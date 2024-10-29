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
import jakarta.persistence.Table;

@Entity
@Table(name="historialpagos")
public class Historialpagos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer HistorialPagoID;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd",iso = ISO.DATE)
	private LocalDate FechaModificacion;
	
	@Column(nullable = false)
	private String EstadoAnterior;
	
	@Column(nullable = false)
	private String EstadoNuevo;
	
	@Column
	private String Descripcion;
	
	@ManyToOne
	@JsonBackReference("pagos-historial")
	@JoinColumn(name = "PagoID",nullable = false)
	private Pagos pagosObj;
	
	public Historialpagos() {
	}

	public Historialpagos(Integer historialPagoID, LocalDate fechaModificacion, String estadoAnterior,
			String estadoNuevo, String descripcion) {
		super();
		HistorialPagoID = historialPagoID;
		FechaModificacion = fechaModificacion;
		EstadoAnterior = estadoAnterior;
		EstadoNuevo = estadoNuevo;
		Descripcion = descripcion;
	}

	public Integer getHistorialPagoID() {
		return HistorialPagoID;
	}

	public void setHistorialPagoID(Integer historialPagoID) {
		HistorialPagoID = historialPagoID;
	}

	public LocalDate getFechaModificacion() {
		return FechaModificacion;
	}

	public void setFechaModificacion(LocalDate fechaModificacion) {
		FechaModificacion = fechaModificacion;
	}

	public String getEstadoAnterior() {
		return EstadoAnterior;
	}

	public void setEstadoAnterior(String estadoAnterior) {
		EstadoAnterior = estadoAnterior;
	}

	public String getEstadoNuevo() {
		return EstadoNuevo;
	}

	public void setEstadoNuevo(String estadoNuevo) {
		EstadoNuevo = estadoNuevo;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public Pagos getPagosObj() {
		return pagosObj;
	}

	public void setPagosObj(Pagos pagosObj) {
		this.pagosObj = pagosObj;
	}
}
