package com.misan.donacionSangre.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.misan.donacionSangre.modelos.Donador;
import com.misan.donacionSangre.modelos.Receptor;
import com.misan.donacionSangre.modelos.SolicitudDonadorReceptor;
import com.misan.donacionSangre.modelos.Usuario;

public interface DonadorRepositorio extends JpaRepository<Donador, Long>{
	
	public Donador findByUsuario(Usuario usuario);
	
	@Query("FROM Donador d join d.solicitudes soli WHERE d.activo= true AND  soli.id in :idSolicitudes ")
	public List<Donador> findAllDonadoresConSolicitud(@Param("idSolicitudes")List<Long> idSolicitudes);
	
	@Query("FROM Donador d WHERE d.activo= true AND  d not in :donadores ")
	public List<Donador> findAllDonadoresSinSolicitud(@Param("donadores")List<Donador> donadores);
	
	@Query("FROM Donador d join d.solicitudes soli WHERE soli.aceptada = false AND soli.enviaSolicitud = :enviaSolicitud")
	public Donador findAllSolicitudesEnviadas(@Param("enviaSolicitud") long enviaSolicitud);
	
	@Query("FROM Donador d join d.solicitudes soli WHERE soli.aceptada = false AND soli.recibeSolicitud = :recibeSolicitud")
	public Donador findAllSolicitudesRecibida(@Param("recibeSolicitud") long recibeSolicitud);
	
	@Query("FROM Donador d join d.solicitudes soli WHERE soli in :solicitudes")
	public List<Donador> findAllReceptorDonadorEncontrado(@Param("solicitudes") List<SolicitudDonadorReceptor> solicitudes);
	
	@Query("FROM Donador d WHERE d.activo = true")
	public List<Donador> findAllDonadores();
	
	@Query("FROM Donador don join don.solicitudes soli WHERE soli.receptor = :Receptor and soli.aceptada = true")
	public List<Donador> findAllAceptados(@Param("Receptor") Receptor receptor);
	
	@Query("FROM Donador don join don.solicitudes soli WHERE don.activo = true and soli.receptor = :Receptor")
	public List<Donador> findAllDonadoresConSolicitudes(@Param("Receptor")Receptor receptor);
	
	@Query("FROM Donador don  WHERE don not in :Donadores")
	public List<Donador> findAllDonadoresSinSolicitudes(@Param("Donadores")List<Donador> donador);
	
	
}
