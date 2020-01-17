package com.franki.apirest1.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.franki.apirest1.model.Cliente;
import com.franki.apirest1.model.Region;

public interface ClienteRep extends JpaRepository<Cliente, Long> {
	
	public Page<Cliente> findAll(Pageable page);
	
	
	@Query("from Region")
	public List<Region> findAllRegiones();

}
