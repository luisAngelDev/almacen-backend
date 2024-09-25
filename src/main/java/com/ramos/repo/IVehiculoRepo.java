package com.ramos.repo;

import org.springframework.stereotype.Repository;

import com.ramos.model.Vehiculo;

@Repository
public interface IVehiculoRepo extends IGenericRepo<Vehiculo, Integer> {

}
