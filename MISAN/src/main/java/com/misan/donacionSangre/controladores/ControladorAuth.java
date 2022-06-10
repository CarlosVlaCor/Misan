package com.misan.donacionSangre.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.misan.donacionSangre.dto.UsuarioDTO;
import com.misan.donacionSangre.servicio.UsuarioServicio;

@RestController
@RequestMapping("/api/")
public class ControladorAuth {
	
	@Autowired
	private UsuarioServicio usuarioServicio;
	@PostMapping("/registro")
	public ResponseEntity<String> registrarUsuario(@RequestBody UsuarioDTO usuarioDTO){
		System.out.println(usuarioDTO);
		UsuarioDTO usuarioRespuesta = usuarioServicio.registrarUsuario(usuarioDTO);
		if(usuarioRespuesta != null) {
			return new ResponseEntity<>("Usuario registrado", HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>("No se registró", HttpStatus.BAD_REQUEST);
		}
	}
}
