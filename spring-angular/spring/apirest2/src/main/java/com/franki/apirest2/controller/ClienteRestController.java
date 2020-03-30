package com.franki.apirest2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.franki.apirest2.model.Cliente;
import com.franki.apirest2.repository.ClienteRep;

@RestController
@RequestMapping("/api/")
public class ClienteRestController {
	@Autowired
	ClienteRep _cliente;
	
	@Secured("ROLE_USER")
	@GetMapping("/prueba")
	private String prueba() {
		return "Todo ok";
	}
	
	@GetMapping("/clientes")
	private List<Cliente> getClientes() {
		return _cliente.findAll();

	}
	

}
