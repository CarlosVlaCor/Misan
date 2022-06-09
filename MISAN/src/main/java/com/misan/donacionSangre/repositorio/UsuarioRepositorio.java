package com.misan.donacionSangre.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.misan.donacionSangre.modelos.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{

}
