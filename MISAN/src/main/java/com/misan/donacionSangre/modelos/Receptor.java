package com.misan.donacionSangre.modelos;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity
@Table(name = "receptores")
public class Receptor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private boolean activo;
	private Date fecha;
	@OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "id_tipo_sangre")
	@JsonBackReference
	private TipoSangre tipoSangre;
	
	
	@OneToMany(mappedBy = "receptor")
	private List<SolicitudDonadorReceptor> solicitudes;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Receptor() {
		super();
	}

	@Override
	public String toString() {
		return "Receptor [id=" + id + ", activo=" + activo + ", fecha=" + fecha + "]";
	}
	
	
	
}
