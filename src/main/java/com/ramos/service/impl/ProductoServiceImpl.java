package com.ramos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramos.model.Producto;
import com.ramos.repo.IGenericRepo;
import com.ramos.repo.IProductoRepo;
import com.ramos.service.IProductoService;

@Service
public class ProductoServiceImpl extends CRUDImpl<Producto, Integer> implements IProductoService {

	@Autowired
	private IProductoRepo repo;
	
	@Override
	protected IGenericRepo<Producto, Integer> getRepo() {
		return repo;
	}

}
