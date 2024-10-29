package com.peloteros.app.peloteros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.peloteros.app.peloteros.entity.Productos;


public interface ProductosRepository extends JpaRepository<Productos, Integer>{
	
	@Query(value="select count(*) from productos where NombreProd=?",nativeQuery = true)
	public abstract int isExistNombre(String NombreProd);

}
