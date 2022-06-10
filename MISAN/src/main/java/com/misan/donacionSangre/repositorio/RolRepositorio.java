package com.misan.donacionSangre.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.misan.donacionSangre.modelos.Rol;

public interface RolRepositorio extends JpaRepository<Rol, Long>{
	
	public Optional<Rol> findByNombre(String nombre);
}
