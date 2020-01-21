package com.franki.apirest1.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.franki.apirest1.model.Usuario;



public interface UsuarioRep extends CrudRepository<Usuario, Long> {
	
	public Usuario findByUsername(String username); 

	/*
	 * @Query("select u from Usuario u where u.usermane = ?1") public Usuario
	 * findByUsername2(String username);
	 */
}

