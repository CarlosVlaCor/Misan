package com.misan.donacionSangre.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.misan.donacionSangre.dto.AceptarSolicitud;
import com.misan.donacionSangre.dto.CancelarSolicitud;
import com.misan.donacionSangre.servicio.SolicitudDondadorReceptorServicio;
import com.misan.donacionSangre.servicio.UsuarioServicio;

@RestController
@RequestMapping("/api")
public class ControladorSolicitudes {
	@Autowired
	private SolicitudDondadorReceptorServicio solicitud;
	@Autowired
	private UsuarioServicio usuarioServicio;
	@DeleteMapping("/donador/eliminarSolicitud")
	public ResponseEntity<String> cancelarSolicitudDonador(@RequestBody CancelarSolicitud cancelarSolicitud){
		solicitud.eliminarSolicitudDonador(cancelarSolicitud);
		return new ResponseEntity<>("Se eliminó la solicitud",HttpStatus.OK);
	}
	
	@DeleteMapping("/receptor/eliminarSolicitud")
	public ResponseEntity<String> cancelarSolicitudReceptor(@RequestBody CancelarSolicitud cancelarSolicitud){
		solicitud.eliminarSolicitudReceptor(cancelarSolicitud);
		return new ResponseEntity<>("Se eliminó la solicitud",HttpStatus.OK);
	}
	
	@PutMapping("/donador/aceptarSolicitud")
	public ResponseEntity<String> aceptarSolicitudDonador(@RequestBody AceptarSolicitud aceptarSolicitud){
		System.out.println(aceptarSolicitud.getIdReceptor());
		solicitud.aceptarSolicitudDonador(aceptarSolicitud);
		return new ResponseEntity<>("Se aceptó la solicitud",HttpStatus.OK);
	}
	@PutMapping("/receptor/aceptarSolicitud")
	public ResponseEntity<String> aceptarSolicitudReceptor(@RequestBody AceptarSolicitud aceptarSolicitud){
		solicitud.aceptarSolicitudReceptor(aceptarSolicitud);
		return new ResponseEntity<>("Se aceptó la solicitud",HttpStatus.OK);
	
	}
	
	@GetMapping("/solicitudes/aceptadas/{email}")
	public void obtenerSolicitudesAceptadas(@PathVariable(name = "email") String email ){
		System.out.print("DSAADS");
		usuarioServicio.obtenerAceptados(email);
	}
	
	
}
