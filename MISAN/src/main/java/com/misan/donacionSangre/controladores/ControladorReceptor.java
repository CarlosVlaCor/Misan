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
import com.misan.donacionSangre.dto.UsuarioDTO;
import com.misan.donacionSangre.modelos.Receptor;
import com.misan.donacionSangre.servicio.DonadorServicio;
import com.misan.donacionSangre.servicio.ReceptorServicio;

@RestController
@RequestMapping("api/receptor")
public class ControladorReceptor {
	@Autowired
	private ReceptorServicio receptorServicio;
	@Autowired
	private DonadorServicio donadorServicio;
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
	@PostMapping("/enviarSolicitud")
	public ResponseEntity<String> enviarSolicitud(@RequestBody EnvioSolicitudDTO envioSolicitud ){
		System.out.println(envioSolicitud.getEmail());
		System.out.println(envioSolicitud.getId());
		receptorServicio.enviarSolicitud(envioSolicitud);
		return new ResponseEntity<>("Solicitud Enviada",HttpStatus.OK);
	}
	@GetMapping("/obtenerDonadores/{email}")
	public List<DonadorTablaDTO> obtenerDonadores(@PathVariable(name="email")String email){
		return donadorServicio.obtenerDonadores(email);
	}
	@GetMapping("/solicitudesRecibidas/{email}")
	public List<DonadorTablaDTO> obtenerSolicitudesRecibidas(@PathVariable(name="email")String email){
		return receptorServicio.obtenerSolicitudesRecibidas(email);
	}
	
	@GetMapping("/solicitudesEnviadas/{email}")
	public List<DonadorTablaDTO> obtenerSolicitudesEnviadas(@PathVariable(name="email")String email){
		return receptorServicio.obtenerSolicitudesEnviadas(email);
	}
}
