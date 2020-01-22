package com.franki.apirest1.auth;

import java.util.Arrays;

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
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
/*Estos son tipos Abstractos los que se ocupan son los de JWT
 * import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
 * import org.springframework.security.oauth2.provider.token.TokenStore;
*/
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import antlr.LLkAnalyzer;


/*
 * Clase para configurar el endpoint encagado de la seguridad de la aplicacion, i.e el que da los acceso a los clienten que hacen 
 * login para obtener un token
*/


@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
	@Autowired
	InfoAdicionalToken infoAdicionalToken;

	
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

		security.tokenKeyAccess("permitAll()").//Que usuarios pueden PEDIR token mediante el login o enviar sus credenciales 
		checkTokenAccess("isAuthenticated()"); //QUienes puede pedir que se verifique el token para acceder a un recurso no Publico
	}


	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

		clients.inMemory().withClient("angularapp").//Indicamos que clientes("Aplicaciones") pueden ingresar al api, ademas de que indicamos donde se van a guardar
		secret(passwordEncoder.encode("12345") ).//Damos una contraseña al clientes
		scopes("read","write").//Asigna permisos
		authorizedGrantTypes("password", "refresh_token").//tipo de authenticacion en este caso login, tambien puede ser por codigo de auntentificacion, refresh_token renueva el token cuando va a caducar el token, apara que no se tenga que hacer login de nuevo
		accessTokenValiditySeconds(3600).//tiempo en que caduca el token
		refreshTokenValiditySeconds(3600);//cada cuando se refresca el token
	}


	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		/*
		 * Instancia que une en una cadena el token con la informacion de auntificacion,
		 * con la informacion adicional
		 */
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(infoAdicionalToken,accessTokenConverter()));
		
		endpoints.authenticationManager(authenticationManager).
		tokenStore(tokenStore()). //Componente que persites el token con ayuda del accessTokenConverter
		accessTokenConverter(accessTokenConverter()).//componente que Traduce (en ambos sentidoa Decodifica<->Codifica) el JWT para que Oauth2 realice el proceso de auntheticacion, 
		tokenEnhancer(tokenEnhancerChain);//Se añade la cadena
		
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
		jwtAccessTokenConverter.setSigningKey(JwtConfig.RSA_PRIVADA);
		jwtAccessTokenConverter.setVerifierKey(JwtConfig.RSA_PUBLICA);

		return jwtAccessTokenConverter;
	} 
	
	
	
	
}
