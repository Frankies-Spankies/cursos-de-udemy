package com.franki.apirest2.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.franki.apirest2.model.Usuario;


/*
 * Al usar un instancia de UsuarioRep, esta por defecto va a trabajar de manera "Transaccional", 
 * pero en buan parctica que todos los servicions DAO implenten la notacion @Transactional para evitar conflictos de acceso
*/

public interface UsuarioRep extends CrudRepository<Usuario, Long> {
	
	public Usuario findByUsername(String username); 

	/*
	 * @Query("select u from Usuario u where u.usermane = ?1") public Usuario
	 * findByUsername2(String username);
	 */
}

