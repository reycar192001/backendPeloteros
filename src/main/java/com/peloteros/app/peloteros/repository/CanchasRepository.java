package com.peloteros.app.peloteros.repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.peloteros.app.peloteros.entity.BuscarDisponibilidadModel;
import com.peloteros.app.peloteros.entity.Canchas;

public interface CanchasRepository extends JpaRepository<Canchas, Integer>{
	
	@Query(value = "{call obtener_disponibilidad_por_fecha3(:fecha_consulta, :num_jugadores)}", nativeQuery = true)
	public abstract List<Object[]> findDisponibilidadByFechaAndJugadores(
	    @Param("fecha_consulta") Date fechaConsulta, 
	    @Param("num_jugadores") int numJugadores);
}
