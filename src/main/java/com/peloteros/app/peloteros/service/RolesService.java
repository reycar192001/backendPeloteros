package com.peloteros.app.peloteros.service;

import java.util.Collection;

import com.peloteros.app.peloteros.entity.Roles;


public interface RolesService {
	public abstract void insert(Roles roles);
	public abstract void update(Roles roles);
	public abstract void delete(Integer RoleID);
	public abstract Roles findById(Integer RoleID);
	public abstract Collection<Roles> findAll();
	public abstract int isExistNombre(String Nombre);
}
