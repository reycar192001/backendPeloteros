package com.peloteros.app.peloteros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.peloteros.app.peloteros.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

	@Query(value="select count(*) from categoria where nombre_cat=?",nativeQuery = true)
	public abstract int isExistNombre(String NombreCat);
}
