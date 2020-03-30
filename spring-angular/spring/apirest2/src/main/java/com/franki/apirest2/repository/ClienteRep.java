package com.franki.apirest2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.franki.apirest2.model.Cliente;

public interface ClienteRep extends JpaRepository<Cliente, Long>{

}
