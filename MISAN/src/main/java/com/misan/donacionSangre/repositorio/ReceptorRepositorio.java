package com.misan.donacionSangre.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.misan.donacionSangre.modelos.Donador;
import com.misan.donacionSangre.modelos.Receptor;
import com.misan.donacionSangre.modelos.SolicitudDonadorReceptor;
import com.misan.donacionSangre.modelos.Usuario;

public interface ReceptorRepositorio extends JpaRepository<Receptor, Long>{
	
	public Receptor findByUsuario(Usuario usuario);
	
	@Query("FROM Receptor r join r.solicitudes soli WHERE r.activo= true AND  soli.id in :idSolicitudes ")
	public List<Receptor> findAllReceptoresConSolicitud(@Param("idSolicitudes")List<Long> idSolicitudes);
	
	@Query("FROM Receptor r WHERE r.activo= true AND  r not in :receptores ")
	public List<Receptor> findAllReceptoresSinSolicitud(@Param("receptores")List<Receptor> receptores);
	
	@Query("FROM Receptor r join r.solicitudes soli WHERE soli in :solicitudes")
	public List<Receptor> findAllReceptorDonadorEncontrado(@Param("solicitudes") List<SolicitudDonadorReceptor> solicitudes);
	
	@Query("FROM Receptor r WHERE r.activo = true")
	public List<Receptor> findAllReceptores();
	
	@Query("FROM Receptor rec join rec.solicitudes soli WHERE rec.id =:idReceptor and soli.aceptada = true")
	public List<Receptor> findAllAceptados(@Param("idReceptor") long id);
	
	@Query("FROM Receptor rec join rec.solicitudes soli WHERE soli.donador = :Donador and soli.aceptada = true")
	public List<Receptor> findAllAceptados(@Param("Donador") Donador donador);
	
	@Query("FROM Donador don join don.solicitudes soli WHERE soli.receptor = :Receptor and soli.aceptada = true")
	public List<Donador> findAllAceptados(@Param("Receptor") Receptor receptor);
	
	@Query("FROM Receptor rec join rec.solicitudes soli WHERE rec.activo = true and soli.donador = :Donador")
	public List<Receptor> findAllReceptoresConSolicitudes(@Param("Donador")Donador donador);
	
	@Query("FROM Receptor rec  WHERE rec not in :Receptores")
	public List<Receptor> findAllReceptoresSinSolicitudes(@Param("Receptores")List<Receptor> receptores);
	
}
