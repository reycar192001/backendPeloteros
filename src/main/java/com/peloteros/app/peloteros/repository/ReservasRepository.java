package com.peloteros.app.peloteros.repository;

import java.sql.Date;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.peloteros.app.peloteros.entity.Reservas;

public interface ReservasRepository extends JpaRepository<Reservas, Integer>{
	
	@Query(value="SELECT re.ReservaID, re.Estado, pr.ProductoID \r\n"
			+ "from reservas_productos rp\r\n"
			+ "INNER JOIN reservas re ON rp.Reserva_ID = re.ReservaID\r\n"
			+ "INNER JOIN productos pr ON rp.Producto_ID = pr.ProductoID;",nativeQuery = true)
	public abstract Collection<Object[]> findAll_withProductos();
	
	@Query(value="SELECT * from reservas where  fecha_reserva=?1",nativeQuery = true)
	public abstract Collection<Reservas> findAll_withDate(Date fecha);
	
	
	
}
