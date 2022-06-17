package com.misan.donacionSangre.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.misan.donacionSangre.modelos.Pais;

public interface PaisRepositorio extends JpaRepository<Pais, Long>{
	
}
