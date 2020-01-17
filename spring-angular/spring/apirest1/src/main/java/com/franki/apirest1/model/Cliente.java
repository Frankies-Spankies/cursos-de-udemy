package com.franki.apirest1.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


//TODOS LOS CONSTRAIS SON DEL PAQUETE CONSTRAINS PARA PODER ENVIAR MENSAJES DE VALIDACION



@Entity
@Table
public class Cliente implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotEmpty
	@Size(min = 4, max = 8, message = "Debe tener entre 4 y 8 caracteres")
	private String nombre;
	@NotEmpty
	private String apellido;
	@NotEmpty
	@Email
	@Column(unique = false, nullable = false)
	private String email;
	@NotNull(message = "no puede estar vacia la fecha")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	private String imagen;
	
	@NotNull(message = "La region no puede ser vacia")
	@ManyToOne(fetch = FetchType.LAZY)//LAZY inidica que se traiga ese atributo hasta que se llame el metodo getRegion, sin embargo al hacer Listar cliente p.e las regiones no se cargaran
	@JoinColumn(name = "region_id")//Anotacion que dice como se va  llamar el campo forein key, si no se pone por defecto pone NombreAtribito_NombreIdTipoDelAtributo 
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})//Lazy al obetener la regio lo hace mediante un objeto region que funciona como "proxy" y tiene dos atributos mas, esta anotacion indicamos que no queremos ponere estos atributos en el Json
	private Region region;
	
	


	//NOTACION UTLIZADA PARA HACER QUE ESA FUNCION SE EJECUTE ANTES DE QUE SE ALMACEN EL OBJETO EN LA BASDE DE DATOS
	//@PrePersist
	public void preGuardado() {
		createAt = new Date();
	}
	 
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	public String getImagen() {
		return imagen;
	}
	

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
