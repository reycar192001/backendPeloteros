package com.peloteros.app.peloteros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.peloteros.app.peloteros.entity.Canchas;
import com.peloteros.app.peloteros.entity.Usuarios;

public interface CanchasRepository extends JpaRepository<Canchas, Integer>{
	
	
}
