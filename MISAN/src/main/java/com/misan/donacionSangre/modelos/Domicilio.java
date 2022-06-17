package com.misan.donacionSangre.modelos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "domicilios")
public class Domicilio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String ciudad;
	
	@ManyToOne
	@JoinColumn(name = "id_estado")
	private Estado estado;
	@ManyToOne
	@JoinColumn(name = "id_pais")
	private Pais pais;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public Pais getPais() {
		return pais;
	}
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	public Domicilio() {
		super();
	}
	
	
	
	
}
