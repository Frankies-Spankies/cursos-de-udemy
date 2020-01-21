package com.franki.apirest1.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.franki.apirest1.model.Cliente;
import com.franki.apirest1.model.Region;


/*Aqui los tipos genereicos el primero el dica el tipo de regreso de las consultas del CRUD, p.e 
 * 
 *En el primero tipo tenemos que es de tipo Cliente busacar se espera que se mape el resultado a un tipo de la clase Cliente 
 * En el segundo indicamos el tipo del id en este caso de tipo Long
 * 
*/

public interface ClienteRep extends JpaRepository<Cliente, Long> {
	
	public Page<Cliente> findAll(Pageable page);
	
	
	@Query("from Region")
	public List<Region> findAllRegiones();

}
