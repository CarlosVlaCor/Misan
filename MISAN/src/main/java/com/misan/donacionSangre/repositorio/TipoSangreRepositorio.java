package com.misan.donacionSangre.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.misan.donacionSangre.modelos.TipoSangre;

public interface TipoSangreRepositorio extends JpaRepository<TipoSangre, Long>{

}
