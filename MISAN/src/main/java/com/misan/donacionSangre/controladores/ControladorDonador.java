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
import com.misan.donacionSangre.servicio.DonadorServicio;

@RestController
@RequestMapping("api/donador")
public class ControladorDonador {
	
	@Autowired
	private DonadorServicio donadorServicio;
	
	@PostMapping("/serDonador")
	public ResponseEntity<String> serDonador(@RequestBody UsuarioDTO usuarioDTO){
		System.out.println("SAD");
		donadorServicio.serDonador(usuarioDTO);
		return new ResponseEntity<>("Todo fine",HttpStatus.OK);
	}
	
	@DeleteMapping("/{email}")
	public ResponseEntity<String> dejarDeSer(@PathVariable(name = "email") String email){
		donadorServicio.dejarDeSerDonador(email);
		return new ResponseEntity<>("Eliminado como donador",HttpStatus.OK);
	}

}
