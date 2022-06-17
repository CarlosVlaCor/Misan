package com.misan.donacionSangre.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.misan.donacionSangre.modelos.Receptor;
import com.misan.donacionSangre.modelos.SolicitudDonadorReceptor;

public interface SolicitudDonadorReceptorRepositorio extends JpaRepository<SolicitudDonadorReceptor, Long>{
	
	@Query("FROM SolicitudDonadorReceptor WHERE recibeSolicitud = :recibeSolicitud and aceptada = false")
	public List<Receptor> findAllSolicitudesDeReceptoresRecibidas(@Param("recibeSolicitud") long id);
	
	@Query("FROM SolicitudDonadorReceptor WHERE enviaSolicitud = :enviaSolicitud and aceptada = false")
	public List<Receptor> findAllSolicitudesAReceptoresEnviadas(@Param("enviaSolicitud")long id);
	
	@Query("FROM SolicitudDonadorReceptor soli WHERE  soli.donador.id = :idDonador")
	public List<SolicitudDonadorReceptor> findAllByIdDonador(@Param("idDonador") long id);
	
	@Query("FROM SolicitudDonadorReceptor soli WHERE  soli.receptor.id = :idReceptor")
	public List<SolicitudDonadorReceptor> findAllByIdReceptor(@Param("idReceptor") long id);
	
	@Query("SELECT soli.id FROM SolicitudDonadorReceptor soli join soli.donador don WHERE don.id = :idDonador")
	public List<Long> findAllIdDonadores(@Param("idDonador") long id);

	@Query("SELECT soli.id FROM SolicitudDonadorReceptor soli join soli.receptor re WHERE re.id = :idReceptor")
	public List<Long> findAllIdReceptores(@Param("idReceptor") long id);
	
	@Query("FROM SolicitudDonadorReceptor soli join soli.donador don WHERE soli.aceptada = false and soli.enviaSolicitud = :idDonador and don.id = :idDonador ")
	public List<SolicitudDonadorReceptor> findAllByDonadorEnviadas(@Param("idDonador") long idDonador);
	
	@Query("From SolicitudDonadorReceptor soli join soli.receptor rec WHERE soli.aceptada = false and soli.enviaSolicitud = :idReceptor and rec.id = :idReceptor")
	public List<SolicitudDonadorReceptor> findAllByReceptorEnviadas(@Param("idReceptor")long idReceptor);
	
	@Query("FROM SolicitudDonadorReceptor soli join soli.donador don WHERE soli.aceptada = false and soli.recibeSolicitud = :idDonador and don.id = :idDonador ")
	public List<SolicitudDonadorReceptor> findAllByDonadorRecibidas(@Param("idDonador") long idDonador);
	
	@Query("FROM SolicitudDonadorReceptor soli join soli.receptor rec WHERE soli.aceptada = false and soli.recibeSolicitud = :idReceptor and rec.id = :idReceptor ")
	public List<SolicitudDonadorReceptor> findAllByReceptorRecibidas(@Param("idReceptor") long idReceptor);
	
	@Query("FROM SolicitudDonadorReceptor soli join soli.donador don join soli.receptor rec WHERE soli.aceptada = false and don.id = :idDonador and rec.id = :idReceptor")
	public List<SolicitudDonadorReceptor> findSoliByDonadorReceptor(@Param("idDonador")long idDonador,@Param("idReceptor")long idReceptor);

	


}
