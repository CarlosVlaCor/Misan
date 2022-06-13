package com.misan.donacionSangre.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.misan.donacionSangre.modelos.Donador;
import com.misan.donacionSangre.modelos.Receptor;
import com.misan.donacionSangre.modelos.Usuario;

public interface ReceptorRepositorio extends JpaRepository<Receptor, Long>{
	
	public Receptor findByUsuario(Usuario usuario);
}
