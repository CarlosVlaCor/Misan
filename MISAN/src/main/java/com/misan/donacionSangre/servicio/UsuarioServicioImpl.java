package com.misan.donacionSangre.servicio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.misan.donacionSangre.dto.UsuarioDTO;
import com.misan.donacionSangre.modelos.Domicilio;
import com.misan.donacionSangre.modelos.Donador;
import com.misan.donacionSangre.modelos.Estado;
import com.misan.donacionSangre.modelos.Pais;
import com.misan.donacionSangre.modelos.Receptor;
import com.misan.donacionSangre.modelos.Rol;
import com.misan.donacionSangre.modelos.TipoSangre;
import com.misan.donacionSangre.modelos.Usuario;
import com.misan.donacionSangre.repositorio.DonadorRepositorio;
import com.misan.donacionSangre.repositorio.EstadoRepositorio;
import com.misan.donacionSangre.repositorio.PaisRepositorio;
import com.misan.donacionSangre.repositorio.ReceptorRepositorio;
import com.misan.donacionSangre.repositorio.RolRepositorio;
import com.misan.donacionSangre.repositorio.SolicitudDonadorReceptorRepositorio;
import com.misan.donacionSangre.repositorio.TipoSangreRepositorio;
import com.misan.donacionSangre.repositorio.UsuarioRepositorio;

@Service
public class UsuarioServicioImpl implements UsuarioServicio{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired 
	private PaisRepositorio paisRepositorio;
	
	@Autowired 
	private EstadoRepositorio estadoRepositorio;
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	@Autowired TipoSangreRepositorio tipoSangreRepositorio;
	
	@Autowired DonadorRepositorio dondadorRepositorio;
	
	@Autowired ReceptorRepositorio receptorRepositorio;
	
	@Autowired ReceptorServicio receptorServicio;
	
	@Autowired DonadorServicio donadorServicio;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private RolRepositorio rolRepositorio;
	@Autowired
	private SolicitudDonadorReceptorRepositorio solicitud;
	@Override
	public UsuarioDTO registrarUsuario(UsuarioDTO usuarioDTO) {
		Usuario usuario = mapearEntidad(usuarioDTO);
		
		Domicilio domicilio = usuario.getDomicilio();
		if(usuarioRepositorio.existsByEmail(usuario.getEmail())) {
			return null;
		}
		System.out.println(domicilio.getPais().getId());
		//Se busca el pais por el id y se verifica que se encuentre
		Pais pais =paisRepositorio.findById(domicilio.getPais().getId()).get();
		
		if(pais == null) {
			return null;
		}
		//Se busca el estado por el id y se verifica que se encuentre
		Estado estado = estadoRepositorio.findById(domicilio.getEstado().getId()).get();
		
		if(estado == null) {
			return null;
		}
		//Se busca el tipo de sangre por el id y se verifica que se encuentre
		TipoSangre tipoSangre = tipoSangreRepositorio.getById(usuario.getTipoSangre().getId());
		if(tipoSangre == null) {
			return null;
		}
		
		Rol rol = rolRepositorio.findByNombre("ROLE_USER").get();
		usuario.setRoles(Collections.singleton(rol));
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		domicilio.setPais(pais);
		domicilio.setEstado(estado);
		usuario.setTipoSangre(tipoSangre);
		usuario.setDomicilio(domicilio);
		usuarioRepositorio.save(usuario);
		receptorServicio.crearReceptor(usuario);
		donadorServicio.crearDonador(usuario);
		return mapearDTO(usuario);
	}
	
	public void obtenerAceptados(String email) {
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		Receptor receptor = receptorRepositorio.findByUsuario(usuario);
		List<Donador> donadores =dondadorRepositorio.findAllAceptados(receptor);
		List<Usuario> usuarios = usuarioRepositorio.findByDonadores(donadores);
		
		Donador donador = dondadorRepositorio.findByUsuario(usuario);
		List<Receptor> receptores =receptorRepositorio.findAllAceptados(donador);
		List<Usuario> usuarios2 = usuarioRepositorio.findByReceptores(receptores);
		
		for(Usuario uss: usuarios2) {
			System.out.println(uss);
		}
	}
	private UsuarioDTO mapearDTO(Usuario usuario) {
		return modelMapper.map(usuario, UsuarioDTO.class);
	}
	private Usuario mapearEntidad(UsuarioDTO usuarioDTO) {
		return modelMapper.map(usuarioDTO, Usuario.class);
	}

}
