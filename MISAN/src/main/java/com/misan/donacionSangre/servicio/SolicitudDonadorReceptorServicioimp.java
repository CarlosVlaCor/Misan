package com.misan.donacionSangre.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.misan.donacionSangre.dto.AceptarSolicitud;
import com.misan.donacionSangre.dto.CancelarSolicitud;
import com.misan.donacionSangre.modelos.SolicitudDonadorReceptor;
import com.misan.donacionSangre.modelos.Usuario;
import com.misan.donacionSangre.repositorio.SolicitudDonadorReceptorRepositorio;
import com.misan.donacionSangre.repositorio.UsuarioRepositorio;

@Service
public class SolicitudDonadorReceptorServicioimp implements SolicitudDondadorReceptorServicio{
	@Autowired
	private SolicitudDonadorReceptorRepositorio solicitud;
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	
	@Override
	public void eliminarSolicitudDonador(CancelarSolicitud cancelarSolicitud) {
		Usuario usuario = usuarioRepositorio.findByEmail(cancelarSolicitud.getEmail());
		List<SolicitudDonadorReceptor> soli =solicitud.findSoliByDonadorReceptor(usuario.getId(), cancelarSolicitud.getIdReceptor());
		solicitud.delete(soli.get(0));
	}

	@Override
	public void eliminarSolicitudReceptor(CancelarSolicitud cancelarSolicitud) {
		Usuario usuario = usuarioRepositorio.findByEmail(cancelarSolicitud.getEmail());
		List<SolicitudDonadorReceptor> soli =solicitud.findSoliByDonadorReceptor(cancelarSolicitud.getIdReceptor(), usuario.getId());
		solicitud.delete(soli.get(0));
	}

	@Override
	public void aceptarSolicitudDonador(AceptarSolicitud aceptarSolicitud) {
		Usuario usuario = usuarioRepositorio.findByEmail(aceptarSolicitud.getEmail());
		List<SolicitudDonadorReceptor> soli = solicitud.findSoliByDonadorReceptor(usuario.getId(), aceptarSolicitud.getIdReceptor());
		SolicitudDonadorReceptor solicitudAGuardar = soli.get(0);
		solicitudAGuardar.setAceptada(true);
		solicitud.save(solicitudAGuardar);
	}

	@Override
	public void aceptarSolicitudReceptor(AceptarSolicitud aceptarSolicitud) {
		Usuario usuario = usuarioRepositorio.findByEmail(aceptarSolicitud.getEmail());
		List<SolicitudDonadorReceptor> soli = solicitud.findSoliByDonadorReceptor(aceptarSolicitud.getIdReceptor(), usuario.getId());
		SolicitudDonadorReceptor solicitudAGuardar = soli.get(0);
		solicitudAGuardar.setAceptada(true);
		solicitud.save(solicitudAGuardar);
		
	}

}
