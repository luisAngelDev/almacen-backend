package com.ramos.dto;

import java.time.LocalDateTime;
import java.util.List;


import com.ramos.model.Cliente;
import com.ramos.model.Vehiculo;

import javax.validation.constraints.NotNull;

public class VentaDTO {
	
	private Integer idVenta;
	
	@NotNull
	private Vehiculo vehiculo;
	
	@NotNull
	private Cliente cliente;
	
	@NotNull
	private Double precioTotal;
	
	@NotNull
	private LocalDateTime fecha;
	
	@NotNull
	private List<DetalleVentaDTO> detalleVenta;
	
	/**
	 * GETTERS AND SETTERS
	 */

	public Integer getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(Integer idVentas) {
		this.idVenta = idVentas;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<DetalleVentaDTO> getDetalleVenta() {
		return detalleVenta;
	}

	public void setDetalleVenta(List<DetalleVentaDTO> detalleVenta) {
		this.detalleVenta = detalleVenta;
	}

	public Double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(Double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	
	
	
	
	
	

}
