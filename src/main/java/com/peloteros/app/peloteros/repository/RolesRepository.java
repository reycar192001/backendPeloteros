package com.peloteros.app.peloteros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.peloteros.app.peloteros.entity.Roles;


public interface RolesRepository extends JpaRepository<Roles, Integer> {

	@Query(value="select count(*) from Roles where nombre=?",nativeQuery = true)
	public abstract int isExistNombre(String Nombres);

}
