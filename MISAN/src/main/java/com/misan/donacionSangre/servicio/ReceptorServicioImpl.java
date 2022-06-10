package com.misan.donacionSangre.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.misan.donacionSangre.modelos.Receptor;
import com.misan.donacionSangre.modelos.Usuario;
import com.misan.donacionSangre.repositorio.ReceptorRepositorio;

@Service
public class ReceptorServicioImpl implements ReceptorServicio{
	@Autowired
	private ReceptorRepositorio receptorRepositorio;
	
	@Override
	public void crearReceptor(Usuario usuario) {
		Receptor receptor = new Receptor();
		receptor.setActivo(false);
		receptor.setUsuario(usuario);
		receptorRepositorio.save(receptor);
		
	}
	
}
