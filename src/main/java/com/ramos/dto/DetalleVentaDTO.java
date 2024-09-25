package com.ramos.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ramos.model.Producto;
//import com.ramos.model.Venta;

import javax.validation.constraints.NotNull;

public class DetalleVentaDTO {
	
	private Integer idDetalle;
	
	@JsonIgnore
	private VentaDTO venta;
	
	@NotNull
	private Producto producto;
	
	@NotNull
	private Integer cantidad;
	
	@NotNull
	private Double subTotal;

	
	
	/**
	 * GETTERS AND SETTERS
	 */

	
	public Integer getIdDetalle() {
		return idDetalle;
	}

	public void setIdDetalle(Integer idDetalle) {
		this.idDetalle = idDetalle;
	}

	public VentaDTO getVenta() {
		return venta;
	}

	public void setVenta(VentaDTO venta) {
		this.venta = venta;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}
	
	
	
	
	
	
	

}
