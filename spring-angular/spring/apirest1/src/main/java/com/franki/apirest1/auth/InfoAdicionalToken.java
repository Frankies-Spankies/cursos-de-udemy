package com.franki.apirest1.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.franki.apirest1.model.Usuario;
import com.franki.apirest1.repo.UsuarioRep;

/*
 * Clase para añadir informacion adicional al Payload del token que se envia,
 *  en este caso se añade al accessToken 
 *  y la informacion del usuario esta en el parametro authentication
*/
@Component
public class InfoAdicionalToken implements TokenEnhancer{

	@Autowired
	UsuarioRep _usuario;
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Usuario usuario=_usuario.findByUsername(authentication.getName());
		Map<String, Object> info= new HashMap<>();
		info.put("adicional", "Hola que tal! "+authentication.getName());
		info.put("nombre", usuario.getNombre());
		info.put("apellido", usuario.getApellido());
		info.put("email", usuario.getApellido());
		
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		return accessToken;
	}

}
