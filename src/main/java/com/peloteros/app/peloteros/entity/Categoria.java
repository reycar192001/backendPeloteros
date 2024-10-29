package com.peloteros.app.peloteros.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="categoria")
public class Categoria implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer CategoriaID;
	
	@Column(nullable = false)
	private String NombreCat;
	
	@Column(nullable = false)
	private Boolean Estado;
	
	@OneToMany(mappedBy = "categoriaObj")
	@JsonManagedReference("categoria-productos")
	private Collection<Productos> itemProductos=new ArrayList<>();
	
	public Categoria() {
	}

	public Categoria(Integer categoriaID, String nombreCat, Boolean estado) {
		super();
		CategoriaID = categoriaID;
		NombreCat = nombreCat;
		Estado = estado;
	}

	public Integer getCategoriaID() {
		return CategoriaID;
	}

	public void setCategoriaID(Integer categoriaID) {
		CategoriaID = categoriaID;
	}

	public String getNombreCat() {
		return NombreCat;
	}

	public void setNombreCat(String nombreCat) {
		NombreCat = nombreCat;
	}

	public Boolean getEstado() {
		return Estado;
	}

	public void setEstado(Boolean estado) {
		Estado = estado;
	}

	public Collection<Productos> getItemProductos() {
		return itemProductos;
	}

	public void setItemProductos(Collection<Productos> itemProductos) {
		this.itemProductos = itemProductos;
	}
}
