package com.misan.donacionSangre.dto;

public class AceptarSolicitud {
	private long idReceptor;
	private String email;
	public long getIdReceptor() {
		return idReceptor;
	}
	public void setIdReceptor(long idReceptor) {
		this.idReceptor = idReceptor;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
