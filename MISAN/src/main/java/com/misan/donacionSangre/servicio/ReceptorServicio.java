package com.misan.donacionSangre.servicio;

import com.misan.donacionSangre.dto.UsuarioDTO;
import com.misan.donacionSangre.modelos.Usuario;

public interface ReceptorServicio {
	
	public void crearReceptor(Usuario usuario);

	public void serReceptor(UsuarioDTO usuarioDTO);

	public void dejarDeSerReceptor(String email);
}
