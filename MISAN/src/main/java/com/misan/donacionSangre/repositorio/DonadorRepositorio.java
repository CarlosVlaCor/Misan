package com.misan.donacionSangre.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.misan.donacionSangre.modelos.Donador;

public interface DonadorRepositorio extends JpaRepository<Donador, Long>{

}
