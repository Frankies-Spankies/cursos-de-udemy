package com.franki.apirest1.auth;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/*
 * Clase para configurar el endpoint del ResorceServer por parte de Oauth.
 * 
*/
@Configuration
@EnableResourceServer //Habilita el servidor de recursos
public class ResouceServerConfig extends ResourceServerConfigurerAdapter{

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/clientes", "/api/clientes/page/**", "/api/clientes/upload/img/**", "/images/**").permitAll().
				/*
				 * antMatchers(HttpMethod.GET,"/api/clientes/{id}").hasAnyRole("USER","ADMIN").
				 * antMatchers(HttpMethod.POST,"/api/clientes/upload").hasAnyRole("USER","ADMIN").
				 * antMatchers(HttpMethod.POST,"/api/clientes").hasRole("ADMIN").
				 * antMatchers("/api/clientes/**").hasRole("ADMIN").
				 */
		//todos con terminen con /api/clientes/** menos los mencionados arriba
		anyRequest().authenticated().
		and().
		cors().configurationSource(corsConfigurationSource());//Configura el cors
	}
	
	/*
	 * CORS Cross Origin Resource Permite que se realizen peticiones dentro de otro
	 * dominio que no se encuentren donde esta corriendo la aplicacion de spring
	 */
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));//Algunos navegadores al enviar el request del login para solicitar un token lo hacen por OPTIONS
		config.setAllowCredentials(true);
		config.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		
		return source;
	}
	
	/*
	 * Crea un filtro de cors con el cors que configuramos arriba
	 * corsConfigurationSource Se le dio a ese filtro la mayor pescedencia de los
	 * filtros registrados en spring
	 * 
	 */
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter(){
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}

	

	


	
}
