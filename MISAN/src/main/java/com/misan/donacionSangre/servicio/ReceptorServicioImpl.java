package com.misan.donacionSangre.servicio;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.misan.donacionSangre.dto.UsuarioDTO;
import com.misan.donacionSangre.modelos.Donador;
import com.misan.donacionSangre.modelos.Receptor;
import com.misan.donacionSangre.modelos.Rol;
import com.misan.donacionSangre.modelos.TipoSangre;
import com.misan.donacionSangre.modelos.Usuario;
import com.misan.donacionSangre.repositorio.DonadorRepositorio;
import com.misan.donacionSangre.repositorio.ReceptorRepositorio;
import com.misan.donacionSangre.repositorio.RolRepositorio;
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
	
	@Override
	public void crearReceptor(Usuario usuario) {
		Receptor receptor = new Receptor();
		receptor.setActivo(false);
		receptor.setUsuario(usuario);
		receptorRepositorio.save(receptor);
		
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
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		Receptor receptor = receptorRepositorio.findByUsuario(usuario);
		receptor.setActivo(false);
		receptor.setFecha(null);
		receptor.setTipoSangre(null);
		
		Set<Rol> roles = usuario.getRoles();
		roles.forEach(rol -> {
			if(rol.getNombre().equals("ROLE_RECEPTOR")) {
				System.out.println("SDAasd");
				roles.remove(rol);
			}
		});
		usuario.setRoles(roles);
		
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
	
}
