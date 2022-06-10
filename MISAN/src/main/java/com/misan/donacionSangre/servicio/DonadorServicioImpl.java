package com.misan.donacionSangre.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.misan.donacionSangre.modelos.Donador;
import com.misan.donacionSangre.modelos.Usuario;
import com.misan.donacionSangre.repositorio.DonadorRepositorio;

@Service
public class DonadorServicioImpl implements DonadorServicio{

	@Autowired
	private DonadorRepositorio donadorRepositorio;
	
	@Override
	public void crearDonador(Usuario usuario) {
		Donador donador = new Donador();
		donador.setActivo(false);
		donador.setUsuario(usuario);
		donadorRepositorio.save(donador);
		
	}
}
