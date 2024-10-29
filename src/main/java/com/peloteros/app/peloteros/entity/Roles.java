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
@Table(name="roles")
public class Roles implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer RoleID;
	
	@Column(unique = true,nullable = false)
	private String Nombre;
	
	@OneToMany(mappedBy = "rolesObj")
	@JsonManagedReference("roles-usuarios")
	private Collection<Usuarios> itemUsuarios=new ArrayList<>();

	public Roles() {
	}

	public Roles(Integer roleID, String nombre) {
		super();
		RoleID = roleID;
		Nombre = nombre;
	}

	public Integer getRoleID() {
		return RoleID;
	}

	public void setRoleID(Integer roleID) {
		RoleID = roleID;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public Collection<Usuarios> getItemUsuarios() {
		return itemUsuarios;
	}

	public void setItemUsuarios(Collection<Usuarios> itemUsuarios) {
		this.itemUsuarios = itemUsuarios;
	}
	
}
