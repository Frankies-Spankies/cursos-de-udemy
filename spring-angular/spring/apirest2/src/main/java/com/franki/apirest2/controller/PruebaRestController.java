package com.franki.apirest2.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	@GetMapping("/uinicio")

	private ResponseEntity<?>  uinicio() {
		Map<String, Object> respuesta = new HashMap<>();
		respuesta.put("mensaje", "Respuesta a Usuario desde servidor");
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/ainicio")
	private ResponseEntity<?> ainicio() {
		Map<String, Object> respuesta = new HashMap<>();
		respuesta.put("mensaje", "Respuesta a Admin desde servidor");
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	@GetMapping("/inicio")
	private ResponseEntity<?> inicio() {
		Map<String, Object> respuesta = new HashMap<>();
		respuesta.put("mensaje", "Respuesta a publico desde servidor");
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);

	}

}
