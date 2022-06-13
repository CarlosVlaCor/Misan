package com.misan.donacionSangre.servicio;

import com.misan.donacionSangre.dto.UsuarioDTO;
import com.misan.donacionSangre.modelos.Usuario;

public interface DonadorServicio {
	
	public void crearDonador(Usuario usuario);
	
	public boolean serDonador(UsuarioDTO usuarioDTO);
	
	public boolean dejarDeSerDonador(String email);
}
