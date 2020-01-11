package com.franki.apirest1.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
	public ResponseEntity<?> getCliente(@PathVariable Long id) {
		Cliente cliente= _cliente.findById(id).orElse(null); 
		Map<String , Object> respuesta= new HashMap<>();
		if (cliente == null) {
			respuesta.put("mensaje", "No se encontro el usuario con el id: "+id);
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}
	
	
	@PostMapping("/clientes")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<?>  crearCliente(@Valid @RequestBody Cliente cliente, BindingResult errores) {
		if(errores.hasErrors()) {
			List<String> errors = new ArrayList<>();
			for (FieldError err : errores.getFieldErrors()) {
				String mensaje= "Campo "+err.getField()+":"+err.getDefaultMessage();
				errors.add(mensaje);
			}
			return new ResponseEntity<List<String>>(errors, HttpStatus.BAD_REQUEST);	
		}
		
		Map<String , Object> respuesta= new HashMap<>();
		try{
			Cliente nuevo= _cliente.save(cliente);
		}catch(DataAccessException e) {
			respuesta.put("mensaje", "No se pudo realizar la insercion del cliente");
			respuesta.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
		}
		respuesta.put("mensaje","Usuario agregado");
		respuesta.put("cliente", cliente );
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
	}
	
	
	//==================
	//Es importante que los errores vallan antes del id en los parametros!!!!!!!!!!
	//==================
	@PutMapping("/clientes/{id}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<?> actualizaCliente(@Valid @RequestBody Cliente cliente, BindingResult errores, @PathVariable Long id) {
		if(errores.hasErrors()) {
			List<String> errors = new ArrayList<>();
			for (FieldError err : errores.getFieldErrors()) {
				String mensaje= "Campo:"+err.getField()+":"+err.getDefaultMessage();
				errors.add(mensaje);
			}
			return new ResponseEntity<List<String>>(errors, HttpStatus.BAD_REQUEST);	
		}
		Map<String , Object> respuesta= new HashMap<>();
		Cliente actual = _cliente.findById(id).orElse(null);
		
		if (actual == null) {
			respuesta.put("mensaje", "No se encontro el usuario con el id: "+id);
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
		}
		
		actual.setApellido(cliente.getApellido());
		actual.setNombre(cliente.getNombre());
		actual.setEmail(cliente.getEmail());		
		try{
		actual= _cliente.save(actual);;
		}catch(DataAccessException e) {
			respuesta.put("mensaje", "No se pudo realizar la actualizacion del cliente");
			respuesta.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
		}
		respuesta.put("mensaje","Usuario agregado");
		respuesta.put("cliente", actual );
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}
	
	@DeleteMapping("/clientes/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseEntity<?> borrarCliente(@PathVariable Long id) {
		Map<String , Object> respuesta= new HashMap<>();
		try{
		_cliente.deleteById(id);
		}catch(DataAccessException e) {
			respuesta.put("mensaje", "No se pudo eliminar el cliente");
			respuesta.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		} 
		respuesta.put("mensaje","Cliente eliminado exitosamente");
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);

	}
	
	
}
