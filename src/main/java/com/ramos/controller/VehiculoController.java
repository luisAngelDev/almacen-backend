package com.ramos.controller;

import java.net.URI;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ramos.dto.VehiculoDTO;
import com.ramos.exception.ModeloNotFoundException;
import com.ramos.model.Vehiculo;
import com.ramos.service.IVehiculoService;

import javax.validation.Valid;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {
	
	@Autowired
	private IVehiculoService service;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping
	public ResponseEntity<List<VehiculoDTO>> listar() throws Exception {
		List<VehiculoDTO> lista = service.listar().stream().map(p -> mapper.map(p, VehiculoDTO.class)).collect(Collectors.toList());
		
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<VehiculoDTO> listarPorId(@PathVariable("id") Integer id) throws Exception {
		Vehiculo obj = service.listarPorId(id);
		
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
						
		VehiculoDTO dto = mapper.map(obj, VehiculoDTO.class);
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	
	
	@PostMapping
	public ResponseEntity<Void> registrar(@Valid @RequestBody VehiculoDTO dto) throws Exception {
		Vehiculo p = mapper.map(dto, Vehiculo.class);
		Vehiculo obj = service.registrar(p);
		
		//localhost:8080/pacientes/5
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdVehiculo()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<VehiculoDTO> modificar(@Valid @RequestBody VehiculoDTO dto) throws Exception {
		Vehiculo obj = service.listarPorId(dto.getIdVehiculo());
		
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + dto.getIdVehiculo());
		}
		
		Vehiculo p = mapper.map(dto, Vehiculo.class);		
		Vehiculo pac = service.modificar(p);
		VehiculoDTO dtoResponse = mapper.map(pac, VehiculoDTO.class);
		return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
		Vehiculo obj = service.listarPorId(id);
		
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		service.eliminar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	

}
