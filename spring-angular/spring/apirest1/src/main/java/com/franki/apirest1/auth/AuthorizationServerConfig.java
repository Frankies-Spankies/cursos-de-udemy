package com.franki.apirest1.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	
	/*
	 * De manera automatica cada vez que se nesesite un BCryptPasswordEncoder va a
	 * llamar al metodo anotado con Bean passwordEncoder()
	 */
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	/*
	 * Con qualifier nos aseguramos de que el AuthenticationManager corresponde
	 * especificamente al metodo authenticationManager que este caso es el que se
	 * configuro y es el que queremos ocupar en caso de que haya otro Bean que
	 * regrese un AuthenticationManager
	 */
	
	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authenticationManager;


	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {

		super.configure(security);
	}


	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

		super.configure(clients);
	}


	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		
		endpoints.authenticationManager(authenticationManager).
		tokenStore(tokenStore()). //Componente que persites el token con ayuda del accessTokenConverter
		accessTokenConverter(accessTokenConverter()).//componente que Traduce (en ambos sentidoa Decodifica<->Codifica) el JWT para que Oauth2 realice el proceso de auntheticacion, 
		
	}

	/*
	 * Manera explicita ya que por defecto al ocupar una instancia de
	 * JwtAccessTokenConverter en la implentacion
	 * AuthorizationServerEndpointsConfigurer, Este crea un instancia de
	 * JwtAccessTokenConverter y la asigna al tokenStore del objeto
	 */
	@Bean  
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}


	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		return jwtAccessTokenConverter;
	} 
	
	
	
	
}
