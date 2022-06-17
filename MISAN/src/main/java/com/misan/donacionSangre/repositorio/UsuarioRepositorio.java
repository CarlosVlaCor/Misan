package com.misan.donacionSangre.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.misan.donacionSangre.modelos.Donador;
import com.misan.donacionSangre.modelos.Receptor;
import com.misan.donacionSangre.modelos.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{
	
	public Usuario findByEmail(String email);
	
	public boolean existsByEmail(String email);
	
	@Query("FROM Usuario us join us.donador don  WHERE don in :donadores ")
	public List<Usuario> findByDonadores(@Param("donadores")List<Donador> donadores);
	
	@Query("FROM Usuario us join us.receptor rec  WHERE rec in :receptores ")
	public List<Usuario> findByReceptores(@Param("receptores")List<Receptor> receptores);
}
