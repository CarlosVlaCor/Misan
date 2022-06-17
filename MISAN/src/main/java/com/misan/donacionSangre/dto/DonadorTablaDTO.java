package com.misan.donacionSangre.dto;

import java.util.Date;

public class DonadorTablaDTO {
	private Date fecha;
	private TipoDeSangreTablaDTO tipoSangre;
	private UsuarioTablaDTO usuario;
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public TipoDeSangreTablaDTO getTipoSangre() {
		return tipoSangre;
	}
	public void setTipoSangre(TipoDeSangreTablaDTO tipoSangre) {
		this.tipoSangre = tipoSangre;
	}
	public UsuarioTablaDTO getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioTablaDTO usuario) {
		this.usuario = usuario;
	}
	
}
