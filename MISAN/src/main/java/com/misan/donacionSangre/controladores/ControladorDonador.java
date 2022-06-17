package com.misan.donacionSangre.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.misan.donacionSangre.dto.DonadorTablaDTO;
import com.misan.donacionSangre.dto.EnvioSolicitudDTO;
import com.misan.donacionSangre.dto.ReceptorTablaDTO;
import com.misan.donacionSangre.dto.UsuarioDTO;
import com.misan.donacionSangre.servicio.DonadorServicio;
import com.misan.donacionSangre.servicio.ReceptorServicio;

@RestController
@RequestMapping("api/donador")
public class ControladorDonador {
	
	@Autowired
	private DonadorServicio donadorServicio;
	
	@Autowired
	private ReceptorServicio receptorServicio;
	
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
	@PostMapping("/enviarSolicitud")
	public ResponseEntity<String> enviarSolicitud(@RequestBody EnvioSolicitudDTO envioSolicitud ){
		System.out.println(envioSolicitud.getEmail());
		System.out.println(envioSolicitud.getId());
		donadorServicio.enviarSolicitud(envioSolicitud);
		return new ResponseEntity<>("Solicitud Enviada",HttpStatus.OK);
	}
	@GetMapping("/obtenerReceptores/{email}")
	public List<ReceptorTablaDTO> obtenerReceptores(@PathVariable(name="email")String email){
		return receptorServicio.obtenerReceptores(email);
	}
	@GetMapping("/solicitudesRecibidas/{email}")
	public List<ReceptorTablaDTO> obtenerSolicitudesRecibidas(@PathVariable(name="email")String email){
		return donadorServicio.obtenerSolicitudesRecibidas(email);
	}
	
	@GetMapping("/solicitudesEnviadas/{email}")
	public List<ReceptorTablaDTO> obtenerSolicitudesEnviadas(@PathVariable(name="email")String email){
		return donadorServicio.obtenerSolicitudesEnviadas(email);
	}

}
