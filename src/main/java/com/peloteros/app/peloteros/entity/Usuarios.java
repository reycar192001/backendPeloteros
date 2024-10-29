package com.peloteros.app.peloteros.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="usuarios")
public class Usuarios implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer UsuarioID;
	
	@Column(unique = true,nullable = false)
	private String Nombre;
	
	@Column(unique = true,nullable = false)
	private String Correo;
	
	@Column
	private String Telefono;
	
	@Column(unique = true,nullable = false,length = 60)
	private String Password;
	
	@ManyToOne
	@JsonBackReference("roles-usuarios")
	@JoinColumn(name = "RoleID",nullable = false)
	private Roles rolesObj;
	
	@OneToMany(mappedBy = "usuariosObj")
	@JsonManagedReference("usuarios-reservas")
	private Collection<Reservas> itemReservas=new ArrayList<>();

	public Usuarios() {
	}
	
	public Usuarios(Integer usuarioID, String nombre, String correo, String telefono, String password) {
		super();
		UsuarioID = usuarioID;
		Nombre = nombre;
		Correo = correo;
		Telefono = telefono;
		Password = password;
	}

	public Integer getUsuarioID() {
		return UsuarioID;
	}

	public void setUsuarioID(Integer usuarioID) {
		UsuarioID = usuarioID;
	}
	
	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getCorreo() {
		return Correo;
	}

	public void setCorreo(String correo) {
		Correo = correo;
	}

	public String getTelefono() {
		return Telefono;
	}

	public void setTelefono(String telefono) {
		Telefono = telefono;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public Roles getRolesObj() {
		return rolesObj;
	}

	public void setRolesObj(Roles rolesObj) {
		this.rolesObj = rolesObj;
	}

	public Collection<Reservas> getItemReservas() {
		return itemReservas;
	}

	public void setItemReservas(Collection<Reservas> itemReservas) {
		this.itemReservas = itemReservas;
	}

}
