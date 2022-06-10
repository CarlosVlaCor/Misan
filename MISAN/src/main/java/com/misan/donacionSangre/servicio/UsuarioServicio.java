package com.misan.donacionSangre.servicio;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.misan.donacionSangre.dto.UsuarioDTO;
import com.misan.donacionSangre.modelos.Usuario;


public interface UsuarioServicio {
	
	public UsuarioDTO registrarUsuario(UsuarioDTO usuarioDTO);
	
	
}
