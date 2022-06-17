package com.misan.donacionSangre.servicio;

import java.util.List;

import com.misan.donacionSangre.dto.DonadorTablaDTO;
import com.misan.donacionSangre.dto.EnvioSolicitudDTO;
import com.misan.donacionSangre.dto.ReceptorTablaDTO;
import com.misan.donacionSangre.dto.UsuarioDTO;
import com.misan.donacionSangre.modelos.Usuario;

public interface ReceptorServicio {
	
	public void crearReceptor(Usuario usuario);

	public void serReceptor(UsuarioDTO usuarioDTO);

	public void dejarDeSerReceptor(String email);
	
	public List<ReceptorTablaDTO> obtenerReceptores(String email);

	public void enviarSolicitud(EnvioSolicitudDTO envioSolicitud);

	public List<DonadorTablaDTO> obtenerSolicitudesRecibidas(String email);

	public List<DonadorTablaDTO> obtenerSolicitudesEnviadas(String email);
}
