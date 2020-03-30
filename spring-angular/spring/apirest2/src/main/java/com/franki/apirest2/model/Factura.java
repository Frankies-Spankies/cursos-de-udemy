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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "facturas")
public class Factura implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descripcion;
	private String observacion;
	@Column(name = "create_at")
	@Temporal(value = TemporalType.DATE)
	private Date createAt;
	@ManyToOne(fetch = FetchType.LAZY)
	private Cliente cliente;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	/*
	 * En ManytoOne no hay que ecpecificar lo de join column En este caso si es
	 * necesario agregar @JoinColumn ya que la relacion de intem->factura no
	 * exixte(No se puede obtener una factura dado un intem)
	 * 
	 * El campo factura_id se creara en la tabla ItemFactura
	 */
	@JoinColumn(name = "factura_id")
	private List<ItemFactura> items;

	public Factura() {
		this.items = new ArrayList<>();
	}

	@PrePersist
	public void preGuardado() {
		createAt = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemFactura> getItems() {
		return items;
	}

	public void setItems(List<ItemFactura> items) {
		this.items = items;
	}

	private Double getTotal() {
		
		Double total=0.0;
		for (ItemFactura itemFactura : items) {
			total+=itemFactura.getImporte();
		}
		return total;

	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
