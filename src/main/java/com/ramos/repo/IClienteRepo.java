package com.ramos.repo;

import org.springframework.stereotype.Repository;

import com.ramos.model.Cliente;

@Repository
public interface IClienteRepo extends IGenericRepo<Cliente, Integer> {

}
