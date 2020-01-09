package com.franki.apirest1.repo;

import org.springframework.data.repository.CrudRepository;

import com.franki.apirest1.model.Cliente;

public interface ClienteRep extends CrudRepository<Cliente, Long> {
	

}
