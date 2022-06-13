package com.misan.donacionSangre.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.misan.donacionSangre.modelos.Donador;
import com.misan.donacionSangre.modelos.Usuario;

public interface DonadorRepositorio extends JpaRepository<Donador, Long>{
	
	public Donador findByUsuario(Usuario usuario);
}
