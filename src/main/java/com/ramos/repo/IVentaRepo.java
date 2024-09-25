package com.ramos.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ramos.model.Venta;

@Repository
public interface IVentaRepo extends IGenericRepo<Venta, Integer> {
	
	//JPQL JAVA PERSISTENCE QUERY LANGUAJE
	@Query("FROM Venta v WHERE v.cliente.dni = :dni OR LOWER(v.cliente.nombre) LIKE %:nombreCompleto% OR LOWER(v.cliente.apellido) LIKE %:nombreCompleto%")
	List<Venta> buscar(@Param("dni") String dni,@Param("nombreCompleto") String nombreCompleto);
	
	//12-02-2024 19-02-2024
	@Query("FROM Venta v WHERE v.fecha BETWEEN :fechaVenta1 AND :fechaVenta2" )
	List<Venta> buscarFecha(@Param("fechaVenta1") LocalDateTime fechaVenta, @Param("fechaVenta2") LocalDateTime fechaVenta2);
	
	@Query(value = "select * from fn_listarResumen()", nativeQuery = true)
	List<Object[]> listarResumen();

	
}
