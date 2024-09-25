package com.ramos.service;

import java.time.LocalDateTime;
import java.util.List;

import com.ramos.dto.VentaResumenDTO;
import com.ramos.model.Venta;

public interface IVentaService extends ICRUD<Venta, Integer> {
	
	Venta registrarTransaccional(Venta venta) throws Exception;
	
	List<Venta> buscar(String dni, String nombreCompleto);
	
	List<Venta> buscarFecha(LocalDateTime fecha1, LocalDateTime fecha2);
	
	List<VentaResumenDTO> listarResumen();
	
	byte[] generarReporte();
	
	
	/*
	 * DEVUELVE TOTAL DE GASTOS POR DIA, MES O AÑO
	 * select (sum(e.monto)::float8) as total, to_char(e.fecha, 'dd/MM/yyyy') as fecha 
	 * from EGRESO e
	 * group by to_char(e.fecha, 'dd/MM/yyyy')
	 * order by to_char(e.fecha, 'dd/MM/yyyy') asc
	 */
	

}

