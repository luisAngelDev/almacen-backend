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

import com.ramos.dto.ProductoDTO;
import com.ramos.exception.ModeloNotFoundException;
import com.ramos.model.Producto;
import com.ramos.service.IProductoService;

import javax.validation.Valid;

@RestController
@RequestMapping("/productos")
public class ProductoController {
	
	@Autowired
	private IProductoService service;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping
	public ResponseEntity<List<ProductoDTO>> listar() throws Exception {
		List<ProductoDTO> lista = service.listar().stream().map(p -> mapper.map(p, ProductoDTO.class)).collect(Collectors.toList());
		
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductoDTO> listarPorId(@PathVariable("id") Integer id) throws Exception {
		Producto obj = service.listarPorId(id);
		
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
						
		ProductoDTO dto = mapper.map(obj, ProductoDTO.class);
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	
	
	@PostMapping
	public ResponseEntity<Void> registrar(@Valid @RequestBody ProductoDTO dto) throws Exception {
		Producto p = mapper.map(dto, Producto.class);
		Producto obj = service.registrar(p);
		
		//localhost:8080/pacientes/5
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdProducto()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<ProductoDTO> modificar(@Valid @RequestBody ProductoDTO dto) throws Exception {
		Producto obj = service.listarPorId(dto.getIdProducto());
		
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + dto.getIdProducto());
		}
		
		Producto p = mapper.map(dto, Producto.class);		
		Producto pac = service.modificar(p);
		ProductoDTO dtoResponse = mapper.map(pac, ProductoDTO.class);
		return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
		Producto obj = service.listarPorId(id);
		
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		service.eliminar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	

}
