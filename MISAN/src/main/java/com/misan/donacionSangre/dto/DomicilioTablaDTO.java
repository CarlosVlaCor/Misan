package com.misan.donacionSangre.dto;

public class DomicilioTablaDTO {
	private String ciudad;
	
	private PaisTablaDTO pais;
	
	private EstadoTablaDTO estado;

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public PaisTablaDTO getPais() {
		return pais;
	}

	public void setPais(PaisTablaDTO pais) {
		this.pais = pais;
	}

	public EstadoTablaDTO getEstado() {
		return estado;
	}

	public void setEstado(EstadoTablaDTO estado) {
		this.estado = estado;
	}
	
	
}
