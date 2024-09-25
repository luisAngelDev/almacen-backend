package com.ramos.repo;

import org.springframework.stereotype.Repository;
import com.ramos.model.Usuario;


@Repository
public interface IUsuarioRepo extends IGenericRepo<Usuario, Integer> {
	
	//from usuario where username = ?
	//@Query("FROM Usuario us where us.username = ?")
	//Derived Query
	Usuario findOneByUsername(String username);

}
