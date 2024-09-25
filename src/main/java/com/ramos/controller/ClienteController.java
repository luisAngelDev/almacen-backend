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

import com.ramos.dto.ClienteDTO;
import com.ramos.exception.ModeloNotFoundException;
import com.ramos.model.Cliente;
import com.ramos.service.IClienteService;

import javax.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private IClienteService service;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> listar() throws Exception {
		List<ClienteDTO> lista = service.listar().stream().map(p -> mapper.map(p, ClienteDTO.class)).collect(Collectors.toList());
		
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO> listarPorId(@PathVariable("id") Integer id) throws Exception {
		Cliente obj = service.listarPorId(id);
		
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
						
		ClienteDTO dto = mapper.map(obj, ClienteDTO.class);
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	
	
	@PostMapping
	public ResponseEntity<Void> registrar(@Valid @RequestBody ClienteDTO dto) throws Exception {
		Cliente p = mapper.map(dto, Cliente.class);
		Cliente obj = service.registrar(p);
		
		//localhost:8080/pacientes/5
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdCliente()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<ClienteDTO> modificar(@Valid @RequestBody ClienteDTO dto) throws Exception {
		Cliente obj = service.listarPorId(dto.getIdCliente());
		
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + dto.getIdCliente());
		}
		
		Cliente p = mapper.map(dto, Cliente.class);		
		Cliente pac = service.modificar(p);
		ClienteDTO dtoResponse = mapper.map(pac, ClienteDTO.class);
		return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
		Cliente obj = service.listarPorId(id);
		
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		service.eliminar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	

}
