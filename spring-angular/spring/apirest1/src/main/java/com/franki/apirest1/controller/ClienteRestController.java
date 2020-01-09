package com.franki.apirest1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.franki.apirest1.model.Cliente;
import com.franki.apirest1.repo.ClienteRep;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ClienteRestController {

	@Autowired
	ClienteRep _cliente; 
	
	
	@GetMapping("/clientes") 
	public List<Cliente> getClientes(){
		return (List<Cliente>) _cliente.findAll();
	}
	
	@GetMapping("/clientes/{id}")
	public Cliente getCliente(@PathVariable Long id) {
		return _cliente.findById(id).orElse(null); 
	}
	
	
	@PostMapping("/clientes")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Cliente crearCliente(@RequestBody Cliente cliente) {
		return _cliente.save(cliente);
	}
	
	@PutMapping("/clientes/{id}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Cliente actualizaCliente( @RequestBody Cliente cliente, @PathVariable Long id) {
		Cliente actual = _cliente.findById(id).orElse(null);
		actual.setApellido(cliente.getApellido());
		actual.setNombre(cliente.getNombre());
		actual.setEmail(cliente.getEmail());
		return _cliente.save(actual);
	}
	
	@DeleteMapping("/clientes/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void borrarCliente(@PathVariable Long id) {
		 _cliente.deleteById(id);
	}
	
	
}
