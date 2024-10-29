package com.peloteros.app.peloteros.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.peloteros.app.peloteros.entity.Pagos;


public interface PagosRepository extends JpaRepository<Pagos, Integer>{
	
	@Query(value="select pa.PagoID,pa.MetodoPago.nombre,de.DescuentoID \r\n"
			+ "from pagos_descuentos pd\r\n"
			+ "inner join pagos pa on pd.Pago_ID=pa.PagoID\r\n"
			+ "inner join descuentos de on pd.Descuento_ID=de.DescuentoID;",nativeQuery = true)
	public abstract Collection<Object[]> findAll_withDescuentos();

}
