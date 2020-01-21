package com.franki.apirest1.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.franki.apirest1.model.Usuario;
import com.franki.apirest1.repo.UsuarioRep;

/*
 * UserDetailsService sirve para trabajar con JPA/JDB ...(i.e algo para hacer consultas a la BD) 
 * para realizar el servicio de login
 * Al hacer Autowired de una instancai a de UserDetailsService y ser la unica implementacion concreta y esta anotada con@Service entonces tomara esta implementacion
*/

@Service
public class UsuarioService implements UserDetailsService{
	
	private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);

	
	@Autowired
	private UsuarioRep _usuario;
	
	
	/*
	 * al no usar @Transactiona la conexión queda cerrada en la primera llamada al
	 * repository, luego si tenemos más relaciones con otras entity
	 * (@ManyToOne, @OneToMany etc), al generar el json va a tratar de realizar las
	 * consulta relacionada mediante el fetch lazy (o carga perezosa) para traer los
	 * datos relacionados y ponerlos en el json, pero cuando se invoquen los métodos
	 * getters para generar el json con las relaciones, si no tiene
	 * el @Transactional la sesión queda cerrada y va a lanzar una excepción
	 * relacionada a la session de hibernate.
	 * 
	 * También es importante para operaciones insert, update y delete para manejar
	 * transaciones, commit y rollback.
	 */
	
	
	@Override 
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario =_usuario.findByUsername(username);
		
		if (usuario==null) {
			log.error("No existe el usuario en el sistema "+username);
			throw new UsernameNotFoundException("No existe el usuario en el sistema "+username);
		}
		
		List<GrantedAuthority> authorities= usuario.getRoles().
				stream().
				map(rol -> new SimpleGrantedAuthority(rol.getNombre())). //Cuando es una sola linea   -> ObjetoRegresado  == -> {return ObjetoRegresado}
				peek(autority -> log.info(autority.getAuthority()) ).  //En este punto el map convirtio el Stream en GrantedAuthority
				collect(Collectors.toList());
		
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
	}

}
