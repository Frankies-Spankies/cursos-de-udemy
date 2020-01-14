package com.franki.apirest1.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.franki.apirest1.model.Cliente;

public interface ClienteRep extends JpaRepository<Cliente, Long> {
	
	public Page<Cliente> findAll(Pageable page);
	

}
