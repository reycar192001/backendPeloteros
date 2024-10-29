package com.peloteros.app.peloteros.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="canchas")
public class Canchas implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer CanchaID;
	
	@Column(nullable = false)
	private Integer NumeroCancha;
	
	@Column
	private String Imagen;
	
	@Column
	private String Descripcion;
	
	@Column
	private Double PrecioPorHora;
	
	@Column(nullable = false)
	private String Estado;
	
	@ManyToMany(mappedBy = "itemsCanchas")
	private Set<Horarios> itemsHorarios = new HashSet<>();
	
	public Canchas() {
	}

	public Canchas(Integer canchaID, Integer numeroCancha, String imagen, String descripcion, Double precioPorHora,
			String estado) {
		super();
		CanchaID = canchaID;
		NumeroCancha = numeroCancha;
		Imagen = imagen;
		Descripcion = descripcion;
		PrecioPorHora = precioPorHora;
		Estado = estado;
	}

	public Integer getCanchaID() {
		return CanchaID;
	}

	public void setCanchaID(Integer canchaID) {
		CanchaID = canchaID;
	}

	public Integer getNumeroCancha() {
		return NumeroCancha;
	}

	public void setNumeroCancha(Integer numeroCancha) {
		NumeroCancha = numeroCancha;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public Double getPrecioPorHora() {
		return PrecioPorHora;
	}

	public void setPrecioPorHora(Double precioPorHora) {
		PrecioPorHora = precioPorHora;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	public String getImagen() {
		return Imagen;
	}

	public void setImagen(String imagen) {
		Imagen = imagen;
	}

	public Set<Horarios> getItemsHorarios() {
		return itemsHorarios;
	}

	public void setItemsHorarios(Set<Horarios> itemsHorarios) {
		this.itemsHorarios = itemsHorarios;
	}
	
}
