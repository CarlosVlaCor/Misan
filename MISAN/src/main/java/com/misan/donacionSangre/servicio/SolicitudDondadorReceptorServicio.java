package com.misan.donacionSangre.servicio;

import com.misan.donacionSangre.dto.AceptarSolicitud;
import com.misan.donacionSangre.dto.CancelarSolicitud;

public interface SolicitudDondadorReceptorServicio {
	public void eliminarSolicitudDonador(CancelarSolicitud cancelarSolicitud);

	public void eliminarSolicitudReceptor(CancelarSolicitud cancelarSolicitud);
	
	public void aceptarSolicitudDonador(AceptarSolicitud aceptarSolicitud);

	public void aceptarSolicitudReceptor(AceptarSolicitud aceptarSolicitud);
}
