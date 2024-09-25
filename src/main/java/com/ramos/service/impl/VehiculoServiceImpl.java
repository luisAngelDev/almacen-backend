package com.ramos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramos.model.Vehiculo;
import com.ramos.repo.IVehiculoRepo;
import com.ramos.repo.IGenericRepo;
import com.ramos.service.IVehiculoService;

@Service
public class VehiculoServiceImpl extends CRUDImpl<Vehiculo, Integer> implements IVehiculoService {

	@Autowired
	private IVehiculoRepo repo;
	
	@Override
	protected IGenericRepo<Vehiculo, Integer> getRepo() {
		return repo;
	}

}
