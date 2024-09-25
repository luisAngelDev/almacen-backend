package com.ramos.dto;

import javax.validation.constraints.NotNull;

public class VentaDetPlantillaDTO {
	
	@NotNull
	private VentaDTO venta;

	
	public VentaDTO getVenta() {
		return venta;
	}

	public void setVenta(VentaDTO venta) {
		this.venta = venta;
	}
	
	

}
