package com.misan.donacionSangre.servicio;

import java.util.List;

import com.misan.donacionSangre.dto.DonadorTablaDTO;
import com.misan.donacionSangre.dto.EnvioSolicitudDTO;
import com.misan.donacionSangre.dto.ReceptorTablaDTO;
import com.misan.donacionSangre.dto.UsuarioDTO;
import com.misan.donacionSangre.modelos.Usuario;

public interface DonadorServicio {
	
	public void crearDonador(Usuario usuario);
	
	public boolean serDonador(UsuarioDTO usuarioDTO);
	
	public boolean dejarDeSerDonador(String email);
	
	public void enviarSolicitud(EnvioSolicitudDTO envioSolicitud);
	
	public List<ReceptorTablaDTO> obtenerSolicitudesRecibidas(String email);
	
	public List<ReceptorTablaDTO> obtenerSolicitudesEnviadas(String email);

	public List<DonadorTablaDTO> obtenerDonadores(String email);
}
