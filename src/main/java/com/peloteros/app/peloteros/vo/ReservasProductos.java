package com.peloteros.app.peloteros.vo;

import com.peloteros.app.peloteros.entity.Productos;
import com.peloteros.app.peloteros.entity.Reservas;

public class ReservasProductos {
	
	private Reservas reservas;
	
	private Productos productos;
	
	public ReservasProductos() {
	}

	public ReservasProductos(Reservas reservas, Productos productos) {
		super();
		this.reservas = reservas;
		this.productos = productos;
	}

	public Reservas getReservas() {
		return reservas;
	}

	public void setReservas(Reservas reservas) {
		this.reservas = reservas;
	}

	public Productos getProductos() {
		return productos;
	}

	public void setProductos(Productos productos) {
		this.productos = productos;
	}
}
