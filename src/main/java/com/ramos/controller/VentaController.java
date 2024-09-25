package com.ramos.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.MediaType;

import com.ramos.dto.FiltroConsultaDTO;
import com.ramos.dto.VentaDTO;
import com.ramos.dto.VentaDetPlantillaDTO;
import com.ramos.dto.VentaResumenDTO;
import com.ramos.exception.ModeloNotFoundException;
import com.ramos.model.Venta;
import com.ramos.service.IVentaService;

import javax.validation.Valid;

@RestController
@RequestMapping("/ventas")
public class VentaController {
	
	@Autowired
	private IVentaService service;
	
	@Autowired
	private ModelMapper mapper;

	
	@GetMapping
	public ResponseEntity<List<VentaDTO>> listar() throws Exception {
		List<VentaDTO> lista = service.listar().stream().map(p -> mapper.map(p, VentaDTO.class)).collect(Collectors.toList());
		
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<VentaDTO> listarPorId(@PathVariable("id") Integer id) throws Exception {
		Venta obj = service.listarPorId(id);
		
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
						
		VentaDTO dto = mapper.map(obj, VentaDTO.class);
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Void> registrar(@Valid @RequestBody VentaDetPlantillaDTO dto) throws Exception {
		Venta p = mapper.map(dto.getVenta(), Venta.class);
		Venta obj = service.registrarTransaccional(p);
		
		//localhost:8080/pacientes/5
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdVenta()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	
	@PutMapping
	public ResponseEntity<VentaDTO> modificar(@Valid @RequestBody VentaDTO dto) throws Exception {
		Venta obj = service.listarPorId(dto.getIdVenta());
		
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + dto.getIdVenta());
		}
		
		Venta p = mapper.map(dto, Venta.class);		
		Venta pac = service.modificar(p);
		VentaDTO dtoResponse = mapper.map(pac, VentaDTO.class);
		return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
		Venta obj = service.listarPorId(id);
		
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		service.eliminar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	@GetMapping("/buscar")
	public ResponseEntity<List<VentaDTO>> buscarFecha(@RequestParam(value = "fecha1") String fecha1, @RequestParam(value = "fecha2") String fecha2) {
		List<Venta> ventas = new ArrayList<>();
		
		ventas = service.buscarFecha(LocalDateTime.parse(fecha1), LocalDateTime.parse(fecha2));
						
		List<VentaDTO> ventasDTO = mapper.map(ventas, new org.modelmapper.TypeToken<List<VentaDTO>>() {}.getType());
		
		return new ResponseEntity<>(ventasDTO, HttpStatus.OK);
	}
	
	@PostMapping("/buscar/otros")
	public ResponseEntity<List<VentaDTO>> buscarOtro(@RequestBody FiltroConsultaDTO filtro) {
		List<Venta> ventas = new ArrayList<>();
		
		ventas = service.buscar(filtro.getDni(), filtro.getNombreCompleto());
						
		List<VentaDTO> ventasDTO = mapper.map(ventas, new org.modelmapper.TypeToken<List<VentaDTO>>() {}.getType());
		
		return new ResponseEntity<>(ventasDTO, HttpStatus.OK);
	}
	
	
	@GetMapping("/listarResumen")
	public ResponseEntity<List<VentaResumenDTO>> listarResumen() {
		List<VentaResumenDTO> ventas = new ArrayList<>();
		
		ventas = service.listarResumen();
		
		return new ResponseEntity<>(ventas, HttpStatus.OK);
	}
	
	@GetMapping(value = "/generarReporte", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> generarReporte() {
		byte[] data = null;
		data = service.generarReporte();
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	
	
	
	
	
}
