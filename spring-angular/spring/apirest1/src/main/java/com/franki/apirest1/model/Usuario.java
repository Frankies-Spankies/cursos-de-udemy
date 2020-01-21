package com.franki.apirest1.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/*
 * Al implementar la clase serializable, se puede converir de una clase java a un objetos JSON
Tambie permie almacenar el objeto en una sesion http 
*/

//Inidicamos si El Nombre de la tabla es distinto al nombre de la clase

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, length = 20)
	private String username;

	@Column(length = 60)
	private String password;

	private Boolean enabled;

	/*
	 * Indicamos toda la configuracion de la realcion Usuarios Roles en esta clase
	 * ya que Usuarios es la dueña de la relacion, por lo que aqui es con indicamos
	 * el tipo del fetch, la cascada y el nombre de la tabla intermedia
	 * 
	 * joinColumns para indicar en nombre de la llave foranea de la tabla "Dueña" de
	 * la relacion
	 *
	 * inverseJoinColumns para indicar el nombre de la llave foranea de la tabla
	 * "dependiente"
	 * 
	 * uniqueConstraints Indica que ese para de columnas en conjunto "tupla" no se
	 * puede repetir sinedo que un usurio no puede tener dos veces ese rol
	 * 
	 */
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "usuarios_id"), inverseJoinColumns = @JoinColumn(name = "role_id"), uniqueConstraints = {
			@UniqueConstraint(columnNames = { "usuarios_id", "role_id" }) })
	private List<Role> roles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
