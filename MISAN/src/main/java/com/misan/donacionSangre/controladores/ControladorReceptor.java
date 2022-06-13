package com.misan.donacionSangre.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.misan.donacionSangre.dto.UsuarioDTO;
import com.misan.donacionSangre.servicio.ReceptorServicio;

@RestController
@RequestMapping("api/receptor")
public class ControladorReceptor {
	@Autowired
	private ReceptorServicio receptorServicio;
	
	@PostMapping("/serReceptor")
	public ResponseEntity<String> serReceptor(@RequestBody UsuarioDTO usuarioDTO){
		System.out.println("SAD");
		receptorServicio.serReceptor(usuarioDTO);
		return new ResponseEntity<>("Todo fine",HttpStatus.OK);
	}
	
	@DeleteMapping("/{email}")
	public ResponseEntity<String> dejarDeSer(@PathVariable(name = "email") String email){
		receptorServicio.dejarDeSerReceptor(email);
		return new ResponseEntity<>("Eliminado como receptor",HttpStatus.OK);
	}
}
