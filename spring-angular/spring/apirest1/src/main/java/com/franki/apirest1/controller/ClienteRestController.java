package com.franki.apirest1.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.franki.apirest1.model.Cliente;
import com.franki.apirest1.repo.ClienteRep;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class ClienteRestController {
	
	private static final Logger log = LoggerFactory.getLogger(ClienteRestController.class);


	@Autowired
	ClienteRep _cliente;

	@GetMapping("/clientes")
	public List<Cliente> getClientes() {
		return (List<Cliente>) _cliente.findAll();
	}

	@GetMapping("/clientes/page/{page}")
	public Page<Cliente> getClientesPage(@PathVariable int page) {
		Pageable pagina = PageRequest.of(page, 5);
		return _cliente.findAll(pagina);
	}

	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> getCliente(@PathVariable Long id) {
		Cliente cliente = _cliente.findById(id).orElse(null);
		Map<String, Object> respuesta = new HashMap<>();
		if (cliente == null) {
			respuesta.put("mensaje", "No se encontro el usuario con el id: " + id);
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}

	/*
	 * REQUEST BODY NO SOPORTA EL LOS DATOS ENVIADOS DIRECTAMENTE ENVIADOS POR UN
	 * FORMULARIO (FORM DATA) FUNCIONA UNICAMNETE CON JSON QUE TIENEN LOS MISMO
	 * RTTRIBUTOS AL OBJETO QUE SE TRATA DE PARSEAR
	 */

	@PostMapping("/clientes")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<?> crearCliente(@Valid @RequestBody Cliente cliente, BindingResult errores) {
		if (errores.hasErrors()) {
			List<String> errors = new ArrayList<>();
			for (FieldError err : errores.getFieldErrors()) {
				String mensaje = "Campo " + err.getField() + ":" + err.getDefaultMessage();
				errors.add(mensaje);
			}
			return new ResponseEntity<List<String>>(errors, HttpStatus.BAD_REQUEST);
		}

		Map<String, Object> respuesta = new HashMap<>();
		try {
			Cliente nuevo = _cliente.save(cliente);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "No se pudo realizar la insercion del cliente");
			respuesta.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
		}
		respuesta.put("mensaje", "Usuario agregado");
		respuesta.put("cliente", cliente);
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
	}

	/*
	 * REQUEST PARAM SOPORTA EL LOS DATOS ENVIADOS DIRECTAMENTE ENVIADOS POR UN
	 * FORMULARIO (FORM DATA)
	 */
	@PostMapping("/clientes/upload")
	public ResponseEntity<?> subirArchivo(@RequestParam(name = "archivo") MultipartFile archivo,
			@RequestParam(name = "id") Long id) {
		Map<String, Object> respuesta = new HashMap<>();
		Cliente cliente = _cliente.findById(id).orElse(null);

		if (!archivo.isEmpty()) {

			/* Nombre random sin espacios en blanco */

			String nombreArchivo = UUID.randomUUID().toString() + '-' + archivo.getOriginalFilename().replace(" ", "");
			Path ruta = Paths.get("/Users/francisco/Documents/cursos-de-udemy/spring-angular/spring/files")
					.resolve(nombreArchivo).toAbsolutePath();
			
			/* Hacer log de la ruta */
			log.info(ruta.toString());
			try {
				Files.copy(archivo.getInputStream(), ruta);
			} catch (IOException e) {
				respuesta.put("mensaje", "No se pudo subir la imagen");
				respuesta.put("error", e.getMessage().concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);

			}

			String imagenAnterior = cliente.getImagen();

			if (imagenAnterior != null && imagenAnterior.length() > 0) {
				Path rutaimagenAnterior = Paths
						.get("/Users/francisco/Documents/cursos-de-udemy/spring-angular/spring/files")
						.resolve(imagenAnterior).toAbsolutePath();
				File archivoimagenAnterior = rutaimagenAnterior.toFile();
				if (archivoimagenAnterior.exists() && archivoimagenAnterior.canRead()) {
					archivoimagenAnterior.delete();
				}
			}
			

			cliente.setImagen(nombreArchivo);
			_cliente.save(cliente);
			respuesta.put("cliente", cliente);
			respuesta.put("imagen", nombreArchivo);
		}

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);

	}
	
	
	
	/*
	 * Metodo para descargar una imagen Path
	 * nombreImagen nombreImagen:.+ siento esto una expresion regular diciendo que va el parametro, un punto y algo mas p.e png
	 */	
	
	@GetMapping("/clientes/upload/img/{nombreImagen:.+}")
	public ResponseEntity<Resource> descarga(@PathVariable String nombreImagen){
		
		
		Path rutaImagen = Paths
				.get("/Users/francisco/Documents/cursos-de-udemy/spring-angular/spring/files")
				.resolve(nombreImagen).toAbsolutePath();
		
		log.info(rutaImagen.toString());

		Resource recurso=null;
		try {
			recurso = new UrlResource(rutaImagen.toUri());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (!recurso.exists() && !recurso.isReadable()) {
			throw new RuntimeException("No se pudo cargar la imagen"+nombreImagen);
		}
		
		HttpHeaders cabecera = new HttpHeaders();
		
		/*
		 * En la cabecera forzamos la descarga
		 */		
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename =\""+recurso.getFilename()+"\"");
		
		
		return new ResponseEntity<Resource>(recurso,cabecera, HttpStatus.OK);

	}

	// ==================
	// Es importante que los errores vallan antes del id en los parametros!!!!!!!!!!
	// ==================
	@PutMapping("/clientes/{id}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<?> actualizaCliente(@Valid @RequestBody Cliente cliente, BindingResult errores,
			@PathVariable Long id) {
		if (errores.hasErrors()) {
			List<String> errors = new ArrayList<>();
			for (FieldError err : errores.getFieldErrors()) {
				String mensaje = "Campo:" + err.getField() + ":" + err.getDefaultMessage();
				errors.add(mensaje);
			}
			return new ResponseEntity<List<String>>(errors, HttpStatus.BAD_REQUEST);
		}
		Map<String, Object> respuesta = new HashMap<>();
		Cliente actual = _cliente.findById(id).orElse(null);

		if (actual == null) {
			respuesta.put("mensaje", "No se encontro el usuario con el id: " + id);
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
		}

		actual.setApellido(cliente.getApellido());
		actual.setNombre(cliente.getNombre());
		actual.setEmail(cliente.getEmail());
		try {
			actual = _cliente.save(actual);
			;
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "No se pudo realizar la actualizacion del cliente");
			respuesta.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
		}
		respuesta.put("mensaje", "Usuario agregado");
		respuesta.put("cliente", actual);
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
	}

	@DeleteMapping("/clientes/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseEntity<?> borrarCliente(@PathVariable Long id) {
		Map<String, Object> respuesta = new HashMap<>();
		Cliente cliente=null; 
		
		/* Borra la foto */
		try {
			
		cliente = _cliente.findById(id).orElse(null);

		/* Borra cliente */	
			_cliente.deleteById(id);
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "No se pudo eliminar el cliente");
			respuesta.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		String imagenAnterior = cliente.getImagen();
		if (imagenAnterior != null && imagenAnterior.length()>0) {
			Path rutaimagenAnterior = Paths.get("/Users/francisco/Documents/cursos-de-udemy/spring-angular/spring/files")
					.resolve(imagenAnterior).toAbsolutePath();
			File archivoimagenAnterior= rutaimagenAnterior.toFile();
			if (archivoimagenAnterior.exists() && archivoimagenAnterior.canRead()) {
				archivoimagenAnterior.delete();
			}
		}
		
		respuesta.put("mensaje", "Cliente eliminado exitosamente");
		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);

	}

}
