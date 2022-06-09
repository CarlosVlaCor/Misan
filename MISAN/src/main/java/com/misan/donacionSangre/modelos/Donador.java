package com.misan.donacionSangre.modelos;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "donadores")
public class Donador {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String ubicacion;
	private boolean activo;
	private Date fecha;
	@OneToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "id_tipo_sangre")
	private TipoSangre tipoSangre;
						  
	@OneToMany(mappedBy = "donador")
	private List<SolicitudDonadorReceptor> solicitudes;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public TipoSangre getTipoSangre() {
		return tipoSangre;
	}

	public void setTipoSangre(TipoSangre tipoSangre) {
		this.tipoSangre = tipoSangre;
	}

	public List<SolicitudDonadorReceptor> getSolicitudes() {
		return solicitudes;
	}

	public void setSolicitudes(List<SolicitudDonadorReceptor> solicitudes) {
		this.solicitudes = solicitudes;
	}

	public Donador() {
		super();
	}
	
	
}
