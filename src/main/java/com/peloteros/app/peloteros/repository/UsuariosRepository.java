package com.peloteros.app.peloteros.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.peloteros.app.peloteros.entity.Usuarios;


public interface UsuariosRepository extends JpaRepository<Usuarios, Integer>
{
	@Query(value="select count(*) from usuarios where nombre=?",nativeQuery = true)
	public abstract int isExistNombre(String Nombre);
	
	@Query(value="select * from usuarios where correo=?1",nativeQuery = true)
	public abstract Usuarios findByUsername(String Correo);
	
}
