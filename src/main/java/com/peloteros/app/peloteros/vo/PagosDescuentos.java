package com.peloteros.app.peloteros.vo;

import com.peloteros.app.peloteros.entity.Descuentos;
import com.peloteros.app.peloteros.entity.Pagos;

public class PagosDescuentos {
	
	private Pagos pagos;
	
	private Descuentos descuentos;
	
	public PagosDescuentos() {
	}

	public PagosDescuentos(Pagos pagos, Descuentos descuentos) {
		super();
		this.pagos = pagos;
		this.descuentos = descuentos;
	}

	public Pagos getPagos() {
		return pagos;
	}

	public void setPagos(Pagos pagos) {
		this.pagos = pagos;
	}

	public Descuentos getDescuentos() {
		return descuentos;
	}

	public void setDescuentos(Descuentos descuentos) {
		this.descuentos = descuentos;
	}
}
