package com.peloteros.app.peloteros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.peloteros.app.peloteros.entity.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Integer> {
	
	@Query(value="select count(*) from marca where NombreMarc=?",nativeQuery = true)
	public abstract int isExistNombre(String NombreMarc);

}
