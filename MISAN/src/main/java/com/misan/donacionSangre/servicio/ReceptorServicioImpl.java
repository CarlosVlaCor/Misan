package com.misan.donacionSangre.servicio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.misan.donacionSangre.dto.DonadorTablaDTO;
import com.misan.donacionSangre.dto.EnvioSolicitudDTO;
import com.misan.donacionSangre.dto.ReceptorTablaDTO;
import com.misan.donacionSangre.dto.UsuarioDTO;
import com.misan.donacionSangre.modelos.Donador;
import com.misan.donacionSangre.modelos.Receptor;
import com.misan.donacionSangre.modelos.Rol;
import com.misan.donacionSangre.modelos.SolicitudDonadorReceptor;
import com.misan.donacionSangre.modelos.TipoSangre;
import com.misan.donacionSangre.modelos.Usuario;
import com.misan.donacionSangre.repositorio.DonadorRepositorio;
import com.misan.donacionSangre.repositorio.ReceptorRepositorio;
import com.misan.donacionSangre.repositorio.RolRepositorio;
import com.misan.donacionSangre.repositorio.SolicitudDonadorReceptorRepositorio;
import com.misan.donacionSangre.repositorio.TipoSangreRepositorio;
import com.misan.donacionSangre.repositorio.UsuarioRepositorio;

@Service
public class ReceptorServicioImpl implements ReceptorServicio{
	@Autowired
	private ReceptorRepositorio receptorRepositorio;
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	@Autowired
	private TipoSangreRepositorio tipoSangreRepositorio;
	@Autowired
	private RolRepositorio rolRepositorio;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private DonadorRepositorio donadorRepositorio;
	@Autowired
	private SolicitudDonadorReceptorRepositorio solicitud;
	@Override
	public void crearReceptor(Usuario usuario) {
		Receptor receptor = new Receptor();
		receptor.setActivo(false);
		receptor.setUsuario(usuario);
		receptorRepositorio.save(receptor);
		
	}
	public List<ReceptorTablaDTO> obtenerReceptores(String email){
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		
		List<Receptor> receptoresConSoicitudes = receptorRepositorio.findAllReceptoresConSolicitudes(usuario.getDonador());
		List<Receptor> receptores;
		if(receptoresConSoicitudes.size() != 0) {
			receptores = receptorRepositorio.findAllReceptoresSinSolicitud(receptoresConSoicitudes);
		}else {
			receptores = receptorRepositorio.findAllReceptores();
		}
		
		return receptores.stream().map(receptor -> mapearDTO(receptor)).collect(Collectors.toList());
			
	}
	private ReceptorTablaDTO mapearDTO(Receptor receptor) {
		return modelMapper.map(receptor, ReceptorTablaDTO.class);
	}
	private DonadorTablaDTO mapearDTO(Donador donador) {
		return modelMapper.map(donador, DonadorTablaDTO.class);
	}

	@Override
	public void serReceptor(UsuarioDTO usuarioDTO) {
		Usuario usuario = usuarioRepositorio.findByEmail(usuarioDTO.getEmail());
		Receptor receptor = receptorRepositorio.findByUsuario(usuario);
		TipoSangre tipoSangre = tipoSangreRepositorio.findById(usuarioDTO.getTipoSangre().getId()).get();
		//Registrar como donador
		receptor.setActivo(true);
		receptor.setFecha(new Date());
		receptor.setTipoSangre(tipoSangre);
		Set<Rol> roles = usuario.getRoles();
		Rol rolEncontrado = rolRepositorio.findByNombre("ROLE_RECEPTOR").get();
		roles.add(rolEncontrado);
		
		var mapearRoles =  new ArrayList<GrantedAuthority>();
		for(Rol rol : roles) {
			System.out.println(rol.getNombre());
			mapearRoles.add(new SimpleGrantedAuthority(rol.getNombre()));
		}
		
		receptorRepositorio.save(receptor);
		usuarioRepositorio.save(usuario);
		Authentication newAuth = new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getPassword(),mapearRoles);
	    SecurityContextHolder.getContext().setAuthentication(newAuth);
		
	}

	@Override
	public void dejarDeSerReceptor(String email) {
		System.out.println(email);
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		Receptor receptor = receptorRepositorio.findByUsuario(usuario);
		receptor.setActivo(false);
		receptor.setFecha(null);
		receptor.setTipoSangre(null);
		Rol rolAEliminar= null;
		Set<Rol> roles = usuario.getRoles();
		
		for(Rol rol: roles) {
			if(rol.getNombre().equals("ROLE_RECEPTOR")) {
				rolAEliminar = rol;
			}
		}
		if(rolAEliminar != null) {
			roles.remove(rolAEliminar);
		}
		usuario.setRoles(roles);
		System.out.println("DASSSADDSADASDSASDA");
		var mapearRoles =  new ArrayList<GrantedAuthority>();
		for(Rol rol : roles) {
			System.out.println(rol.getNombre());
			mapearRoles.add(new SimpleGrantedAuthority(rol.getNombre()));
		}
		usuarioRepositorio.save(usuario);
		receptorRepositorio.save(receptor);
        Authentication newAuth = new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getPassword(),mapearRoles);
        SecurityContextHolder.getContext().setAuthentication(newAuth);
		
	}
	@Override
	public void enviarSolicitud(EnvioSolicitudDTO envioSolicitud) {
		Donador donador = donadorRepositorio.findById(envioSolicitud.getId()).get();
		Usuario usuario = usuarioRepositorio.findByEmail(envioSolicitud.getEmail());
		Receptor receptor = receptorRepositorio.findByUsuario(usuario);
		SolicitudDonadorReceptor nuevaSolicitud = new SolicitudDonadorReceptor();
		nuevaSolicitud.setDonador(donador);
		nuevaSolicitud.setReceptor(receptor);
		nuevaSolicitud.setEnviaSolicitud(receptor.getId());
		nuevaSolicitud.setRecibeSolicitud(donador.getId());
		solicitud.save(nuevaSolicitud);
		
	}
	@Override
	public List<DonadorTablaDTO> obtenerSolicitudesRecibidas(String email) {
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		List<SolicitudDonadorReceptor> solicitudes=solicitud.findAllByReceptorRecibidas(usuario.getId());
		List<Donador> donadoresEncontrados = donadorRepositorio.findAllReceptorDonadorEncontrado(solicitudes);
		return donadoresEncontrados.stream().map(donador -> mapearDTO(donador)).collect(Collectors.toList());
	}
	@Override
	public List<DonadorTablaDTO> obtenerSolicitudesEnviadas(String email) {
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		List<SolicitudDonadorReceptor> solicitudes=solicitud.findAllByReceptorEnviadas(usuario.getId());
		List<Donador> donadoresEncontrados = donadorRepositorio.findAllReceptorDonadorEncontrado(solicitudes);
		return donadoresEncontrados.stream().map(donador -> mapearDTO(donador)).collect(Collectors.toList());
	}
	
}
