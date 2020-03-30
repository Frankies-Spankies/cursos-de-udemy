package com.franki.apirest2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

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

	/*
	 * MapedBy es para indicar que la otra parte de la relacion Cliente->FActura
	 * tambien existe
	 * 
	 * Tenemos la opcion cascade ya que si esta si el cliente se elimina algo se
	 * debe hacer con sus facturas
	 * 
	 * Tipo All ya que si se elimina el el cliene elimina todas sus facturas hijas,
	 * de igual manera si se guarda un cliente con facturas primero guarda el
	 * cliente y luego sus facturas
	 */

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Factura> facturas;

	public Cliente() {
		this.facturas = new ArrayList<>();
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

	public List<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
