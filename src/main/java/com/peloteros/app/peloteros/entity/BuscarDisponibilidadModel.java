package com.peloteros.app.peloteros.entity;

import java.sql.Time;

public class BuscarDisponibilidadModel {

	 	private Integer horarioId;
	    private Time horaInicio;
	    private Integer canchaId;
	    private Integer numeroCancha;
	    private Double precioHora;
	    private String estado;
	    
	    public BuscarDisponibilidadModel() {
			super();
		}
	    
		public BuscarDisponibilidadModel(Integer horarioId, Time horaInicio, Integer canchaId, Integer numeroCancha, Double precioHora,
				String estado) {
			super();
			this.horarioId = horarioId;
			this.horaInicio = horaInicio;
			this.canchaId = canchaId;
			this.numeroCancha = numeroCancha;
			this.precioHora = precioHora;
			this.estado = estado;
		}

		public Integer getHorarioId() {
			return horarioId;
		}

		public void setHorarioId(Integer horarioId) {
			this.horarioId = horarioId;
		}

		public Time getHoraInicio() {
			return horaInicio;
		}

		public void setHoraInicio(Time horaInicio) {
			this.horaInicio = horaInicio;
		}

		public Integer getCanchaId() {
			return canchaId;
		}

		public void setCanchaId(Integer canchaId) {
			this.canchaId = canchaId;
		}

		public Integer getNumeroCancha() {
			return numeroCancha;
		}

		public void setNumeroCancha(Integer numeroCancha) {
			this.numeroCancha = numeroCancha;
		}
		
		

		public double getPrecioHora() {
			return precioHora;
		}

		public void setPrecioHora(Double precioHora) {
			this.precioHora = precioHora;
		}

		public String getEstado() {
			return estado;
		}

		public void setEstado(String estado) {
			this.estado = estado;
		}
		
		
	    
	    
}
