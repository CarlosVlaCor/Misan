package com.misan.donacionSangre.dto;

import com.misan.donacionSangre.modelos.Domicilio;

public class UsuarioTablaDTO {
	private long id;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private DomicilioTablaDTO domicilio;
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
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public DomicilioTablaDTO getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(DomicilioTablaDTO domicilio) {
		this.domicilio = domicilio;
	}
	
	
	
}
