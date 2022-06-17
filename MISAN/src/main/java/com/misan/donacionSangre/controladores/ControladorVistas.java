package com.misan.donacionSangre.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class ControladorVistas {
	@RequestMapping("/")
	public String obtenerInicio() {
		return "index.html";
	}
	@RequestMapping("/nosotros")
	public String obtenerNosotros() {
		return "nosotros.html";
	}
	@RequestMapping("/requisitos")
	public String mostrarRequisitos() {
		return "requisitos.html";
	}
	
	@RequestMapping("/mitos")	
	public String obtenerMitos() {
		return "mitos.html";
	}
	
	@RequestMapping("/pf")
	public String obtenerPreguntasFrecuentes(){
		return "pf.html";
	}
	
	@RequestMapping("/registro")
	public String obtenerRegistro() {
		return "registro.html";
	}
	
	@RequestMapping("/login")
	public String obtenerLogin() {
		return "login.html";
	}
	
	@RequestMapping("/ser")
	public String obtenerSer() {
		return "ser.html";
	}
	
	@RequestMapping("/donador")
	public String obtenerDonador() {
		return "donador.html";
	}
	
	@RequestMapping("/receptor")
	public String obtenerReceptor() {
		return "receptor.html";
	}
	@RequestMapping("/solicitudes")
	public String obtenerSolicitudes() {
		return "solicitudes.html";
	}
}
