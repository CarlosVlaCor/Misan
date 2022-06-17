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
public class DonadorServicioImpl implements DonadorServicio{
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private DonadorRepositorio donadorRepositorio;
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	@Autowired
	private TipoSangreRepositorio tipoSangreRepositorio;
	@Autowired
	private RolRepositorio rolRepositorio;
	@Autowired
	private ReceptorRepositorio receptorRepositorio;
	@Autowired
	private SolicitudDonadorReceptorRepositorio solicitud;
	
	@Override
	public void crearDonador(Usuario usuario) {
		Donador donador = new Donador();
		donador.setActivo(false);
		donador.setUsuario(usuario);
		donadorRepositorio.save(donador);
		
	}
	
	public boolean serDonador(UsuarioDTO usuarioDTO) {
		Usuario usuario = usuarioRepositorio.findByEmail(usuarioDTO.getEmail());
		Donador donador = donadorRepositorio.findByUsuario(usuario);
		TipoSangre tipoSangre = tipoSangreRepositorio.findById(usuarioDTO.getTipoSangre().getId()).get();
		//Registrar como donador
		donador.setActivo(true);
		donador.setFecha(new Date());
		donador.setTipoSangre(tipoSangre);
		Set<Rol> roles = usuario.getRoles();
		Rol rolEncontrado = rolRepositorio.findByNombre("ROLE_DONADOR").get();
		roles.add(rolEncontrado);
		
		var mapearRoles =  new ArrayList<GrantedAuthority>();
		for(Rol rol : roles) {
			System.out.println(rol.getNombre());
			mapearRoles.add(new SimpleGrantedAuthority(rol.getNombre()));
		}
		
		donadorRepositorio.save(donador);
		usuarioRepositorio.save(usuario);
		Authentication newAuth = new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getPassword(),mapearRoles);
	    SecurityContextHolder.getContext().setAuthentication(newAuth);
		return true;
	}
	
	public boolean dejarDeSerDonador(String email) {
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		Donador donador = donadorRepositorio.findByUsuario(usuario);
		donador.setActivo(false);
		donador.setFecha(null);
		donador.setTipoSangre(null);
		
		Rol rolAEliminar= null;
		Set<Rol> roles = usuario.getRoles();
		
		for(Rol rol: roles) {
			if(rol.getNombre().equals("ROLE_DONADOR")) {
				rolAEliminar = rol;
			}
		}
		if(rolAEliminar != null) {
			roles.remove(rolAEliminar);
		}
		usuario.setRoles(roles);
		
		var mapearRoles =  new ArrayList<GrantedAuthority>();
		for(Rol rol : roles) {
			System.out.println(rol.getNombre());
			mapearRoles.add(new SimpleGrantedAuthority(rol.getNombre()));
		}
		usuarioRepositorio.save(usuario);
		donadorRepositorio.save(donador);
        Authentication newAuth = new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getPassword(),mapearRoles);
        SecurityContextHolder.getContext().setAuthentication(newAuth);
        return true;
	}
	
	public void enviarSolicitud(EnvioSolicitudDTO envioSolicitud) {
		Receptor receptor = receptorRepositorio.findById(envioSolicitud.getId()).get();
		Usuario usuario = usuarioRepositorio.findByEmail(envioSolicitud.getEmail());
		Donador donador = donadorRepositorio.findByUsuario(usuario);
		SolicitudDonadorReceptor nuevaSolicitud = new SolicitudDonadorReceptor();
		nuevaSolicitud.setDonador(donador);
		nuevaSolicitud.setReceptor(receptor);
		nuevaSolicitud.setEnviaSolicitud(donador.getId());
		nuevaSolicitud.setRecibeSolicitud(receptor.getId());
		solicitud.save(nuevaSolicitud);
		
	}

	@Override
	public List<ReceptorTablaDTO> obtenerSolicitudesRecibidas(String email) {
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		List<SolicitudDonadorReceptor> solicitudes=solicitud.findAllByDonadorRecibidas(usuario.getId());
		List<Receptor> receptoresEncontrados = receptorRepositorio.findAllReceptorDonadorEncontrado(solicitudes);
		return receptoresEncontrados.stream().map(receptor -> mapearDTO(receptor)).collect(Collectors.toList());
	}

	@Override
	public List<ReceptorTablaDTO> obtenerSolicitudesEnviadas(String email) {
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		List<SolicitudDonadorReceptor> solicitudes=solicitud.findAllByDonadorEnviadas(usuario.getId());
		List<Receptor> receptoresEncontrados = receptorRepositorio.findAllReceptorDonadorEncontrado(solicitudes);
		return receptoresEncontrados.stream().map(receptor -> mapearDTO(receptor)).collect(Collectors.toList());
	}
	
	
	@Override
	public List<DonadorTablaDTO> obtenerDonadores(String email){
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		
		List<Donador> donadorConSolicitudes = donadorRepositorio.findAllDonadoresConSolicitudes(usuario.getReceptor());
		List<Donador> donadores;
		
		if(donadorConSolicitudes.size() != 0) {
			 donadores = donadorRepositorio.findAllDonadoresSinSolicitud(donadorConSolicitudes);
		}else {
			 donadores = donadorRepositorio.findAllDonadores();
		}
		
		return donadores.stream().map(donador -> mapearDTO(donador)).collect(Collectors.toList());
		
	}
	
	private DonadorTablaDTO mapearDTO(Donador donador) {
		return modelMapper.map(donador, DonadorTablaDTO.class);
	}
	private ReceptorTablaDTO mapearDTO(Receptor receptor) {
		return modelMapper.map(receptor, ReceptorTablaDTO.class);
	}

	
}
