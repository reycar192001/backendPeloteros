package com.peloteros.app.peloteros.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.peloteros.app.peloteros.entity.Horarios;


public interface HorariosRepository extends JpaRepository<Horarios, Integer>{
	
	@Query(value="select ho.HorarioID,ho.Estado,ca.CanchaID \r\n"
			+ "from horarios_canchas hc\r\n"
			+ "inner join horarios ho on hc.Horario_ID=ho.HorarioID\r\n"
			+ "inner join canchas ca on hc.Cancha_ID=ca.CanchaID;",nativeQuery = true)
	public abstract Collection<Object[]> findAll_withCanchas();
	
}
