package com.peloteros.app.peloteros.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="productos")
public class Productos implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ProductoID;
	
	@Column(nullable = false)
	private String NombreProd;
	
	@Column(nullable = false)
	private String Descripcion;
	
	@Column(nullable = false)
	private Double Precio;
	
	@Column(nullable = false)
	private Integer Stock;
	
	@Column
	private String Imagen;
	
	@Column(nullable = false)
	private Boolean Estado;

	@ManyToMany(mappedBy = "itemsProductos")
	private Set<Reservas> itemsReservas =new HashSet<>();
	
	@ManyToOne
	@JsonBackReference("categoria-productos")
	@JoinColumn(name = "CategoriaID",nullable = false)
	private Categoria categoriaObj;
	
	@ManyToOne
	@JsonBackReference("marca-productos")
	@JoinColumn(name = "MarcaID",nullable = false)
	private Marca marcaObj;
	
	public Productos() {
	}

	public Productos(Integer productoID, String nombreProd, String descripcion, Double precio, Integer stock,
			String imagen, Boolean estado) {
		super();
		ProductoID = productoID;
		NombreProd = nombreProd;
		Descripcion = descripcion;
		Precio = precio;
		Stock = stock;
		Imagen = imagen;
		Estado = estado;
	}

	public Integer getProductoID() {
		return ProductoID;
	}

	public void setProductoID(Integer productoID) {
		ProductoID = productoID;
	}

	public String getNombreProd() {
		return NombreProd;
	}

	public void setNombreProd(String nombreProd) {
		NombreProd = nombreProd;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public Double getPrecio() {
		return Precio;
	}

	public void setPrecio(Double precio) {
		Precio = precio;
	}

	public Integer getStock() {
		return Stock;
	}

	public void setStock(Integer stock) {
		Stock = stock;
	}

	public String getImagen() {
		return Imagen;
	}

	public void setImagen(String imagen) {
		Imagen = imagen;
	}

	public Boolean getEstado() {
		return Estado;
	}

	public void setEstado(Boolean estado) {
		Estado = estado;
	}

	public Set<Reservas> getItemsReservas() {
		return itemsReservas;
	}

	public void setItemsReservas(Set<Reservas> itemsReservas) {
		this.itemsReservas = itemsReservas;
	}

	public Categoria getCategoriaObj() {
		return categoriaObj;
	}

	public void setCategoriaObj(Categoria categoriaObj) {
		this.categoriaObj = categoriaObj;
	}

	public Marca getMarcaObj() {
		return marcaObj;
	}

	public void setMarcaObj(Marca marcaObj) {
		this.marcaObj = marcaObj;
	}
}
