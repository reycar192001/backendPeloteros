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
@Table(name="marca")
public class Marca implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer MarcaID;
	
	@Column(nullable = false)
	private String NombreMarc;
	
	@Column(nullable = false)
	private Boolean Estado;
	
	@OneToMany(mappedBy = "marcaObj")
	@JsonManagedReference("marca-productos")
	private Collection<Productos> itemProductos=new ArrayList<>();
	
	public Marca() {
	}

	public Marca(Integer marcaID, String nombreMarc, Boolean estado) {
		super();
		MarcaID = marcaID;
		NombreMarc = nombreMarc;
		Estado = estado;
	}

	public Integer getMarcaID() {
		return MarcaID;
	}

	public void setMarcaID(Integer marcaID) {
		MarcaID = marcaID;
	}

	public String getNombreMarc() {
		return NombreMarc;
	}

	public void setNombreMarc(String nombreMarc) {
		NombreMarc = nombreMarc;
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
