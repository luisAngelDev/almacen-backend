package com.ramos.dto;

import javax.validation.constraints.NotNull;

public class ProductoDTO {
	
	
	private Integer idProducto;
	
	@NotNull
	private String nombre;
	
	@NotNull
	private String marca;

	@NotNull
	private Integer stock;

	@NotNull
	private Double precioUnitario;
	
	@NotNull
	private String descripcion;

	
	/**
	 * GETTERS AND SETTERS
	 */

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	

	
}
