package com.franki.apirest1.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.franki.apirest1.service.UsuarioService;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService usuarioService;
	
	/*
	 * Mendiante la notacion Bean Registramos en este caso un metodo dentro del
	 * coontenedor de spring y cada vez que se llama a un metdo con ese nombre
	 * spring busca en su contenedor y lo "inyecta" de igual manera con los
	 * Components (para clases) y los servicios
	 */
	
	@Bean 
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/*
	 * Se inyecta via PARAMETRO El AuthenticationManagerBuilder auth 
	 * Al ser inyectado nos aseguramos de que la
	 * proxima vez que se inyecte se va a inyectar con la nueva configuracion en este caso solo auth
	 */
	@Override
	@Autowired 
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioService).passwordEncoder(passwordEncoder());
	}
	
	
	/* 
	 * En este caso lo que registramos es el regreso del AuthenticationManager 
	*/
	@Bean("authenticationManager") //por defecto se registra con el mismo nombre del metodo
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	
	
	
}
