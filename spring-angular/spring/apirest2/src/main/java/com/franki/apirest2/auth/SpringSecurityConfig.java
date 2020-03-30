package com.franki.apirest2.auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/*
 * Clase encargada de la configuracion basica de Spring security, 
 * se configura el Password encoder 
 * y el servicio que que ocupara  para verfiicar que los usuarios estan registrados en BD (UserDetailsService)
*/

@EnableGlobalMethodSecurity(securedEnabled=true) //Permite agregar los reles requeridos en los metodos con la notacion @Secured
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
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().
		anyRequest().authenticated().
		and().//and regresa a la configuracion de Spring security
		csrf().disable().//Cross-site request forgery  -- Cuando se trabaja con un monolito y se quiere evitar que otro cliente fuera de este haga peticiones
		sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//El manejo de sesion tal como los datos del usuario se guardan en los tokens por lo que ya no se nesesaria la sesion
		
	}
	
	
	
	
}
