package com.misan.donacionSangre.modelos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "solicitudesDonadorReceptor")
public class SolicitudDonadorReceptor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private long enviaSolicitud;
	private long recibeSolicitud;
	private boolean aceptada;
	
	@OneToOne
	@JoinColumn(name = "id_resena")
	private Resena resena;
	
	@ManyToOne
	@JoinColumn(name = "id_donador")
	private Donador donador;
	
	@ManyToOne
	@JoinColumn(name = "id_receptor")
	private Receptor receptor;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getEnviaSolicitud() {
		return enviaSolicitud;
	}

	public void setEnviaSolicitud(long enviaSolicitud) {
		this.enviaSolicitud = enviaSolicitud;
	}

	public long getRecibeSolicitud() {
		return recibeSolicitud;
	}

	public void setRecibeSolicitud(long recibeSolicitud) {
		this.recibeSolicitud = recibeSolicitud;
	}

	public boolean isAceptada() {
		return aceptada;
	}

	public void setAceptada(boolean aceptada) {
		this.aceptada = aceptada;
	}

	public Resena getResena() {
		return resena;
	}

	public void setResena(Resena resena) {
		this.resena = resena;
	}

	public Donador getDonador() {
		return donador;
	}

	public void setDonador(Donador donador) {
		this.donador = donador;
	}

	public Receptor getReceptor() {
		return receptor;
	}

	public void setReceptor(Receptor receptor) {
		this.receptor = receptor;
	}

	public SolicitudDonadorReceptor() {
		super();
	}
	
	
	
	
}
