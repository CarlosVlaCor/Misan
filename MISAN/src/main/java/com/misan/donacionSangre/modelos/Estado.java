package com.misan.donacionSangre.modelos;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "estados")
public class Estado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String nombreEstado;
	
	
	@OneToMany(mappedBy = "estado", cascade = CascadeType.ALL)
	private List<Domicilio> domicilios;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getNombreEstado() {
		return nombreEstado;
	}


	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}


	public List<Domicilio> getDomicilios() {
		return domicilios;
	}


	public void setDomicilios(List<Domicilio> domicilios) {
		this.domicilios = domicilios;
	}


	public Estado() {
		super();
	}
	
	
}
