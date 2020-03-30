package com.franki.apirest2.model;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role implements Serializable {



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, length = 20)
	private String nombre;
	
	//Indicamos esta anotacion solo en caso de que se quiere obtener los usuarios por los roles, si no es suficiente con la de usuarios
	/*
	 * @ManyToMany(mappedBy = "roles") private List<Usuario> usuarios;
	 */
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	/* De Igual forma los Getters y Setters
	 * public List<Usuario> getUsuarios() { return usuarios; }
	 * 
	 * public void setUsuarios(List<Usuario> usuarios) { this.usuarios = usuarios; }
	 */





}
