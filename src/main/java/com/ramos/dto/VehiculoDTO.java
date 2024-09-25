package com.ramos.dto;

import javax.validation.constraints.NotNull;

public class VehiculoDTO {
	
	private Integer idVehiculo;
	
	@NotNull
	private String nombre;
	
	@NotNull
	private String modelo;
	
	@NotNull
	private String marca;
	
	@NotNull
	private String color;
	
	

	public Integer getIdVehiculo() {
		return idVehiculo;
	}

	public void setIdVehiculo(Integer idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	
	

}
