package com.peloteros.app.peloteros.vo;

import com.peloteros.app.peloteros.entity.Canchas;
import com.peloteros.app.peloteros.entity.Horarios;

public class HorariosCanchas {
	
	private Horarios horarios;
	
	private Canchas canchas;
	
	public HorariosCanchas() {
	}

	public HorariosCanchas(Horarios horarios, Canchas canchas) {
		super();
		this.horarios = horarios;
		this.canchas = canchas;
	}

	public Horarios getHorarios() {
		return horarios;
	}

	public void setHorarios(Horarios horarios) {
		this.horarios = horarios;
	}

	public Canchas getCanchas() {
		return canchas;
	}

	public void setCanchas(Canchas canchas) {
		this.canchas = canchas;
	}
}
