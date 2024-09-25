package com.ramos.repo;

import org.springframework.stereotype.Repository;

import com.ramos.model.Producto;

@Repository
public interface IProductoRepo extends IGenericRepo<Producto, Integer> {

}
