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
import com.misan.donacionSangre.modelos.Rol;
import com.misan.donacionSangre.modelos.TipoSangre;
import com.misan.donacionSangre.modelos.Usuario;
import com.misan.donacionSangre.repositorio.DonadorRepositorio;
import com.misan.donacionSangre.repositorio.RolRepositorio;
import com.misan.donacionSangre.repositorio.TipoSangreRepositorio;
import com.misan.donacionSangre.repositorio.UsuarioRepositorio;

@Service
public class DonadorServicioImpl implements DonadorServicio{

	@Autowired
	private DonadorRepositorio donadorRepositorio;
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	@Autowired
	private TipoSangreRepositorio tipoSangreRepositorio;
	@Autowired
	private RolRepositorio rolRepositorio;
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
		
		Set<Rol> roles = usuario.getRoles();
		roles.forEach(rol -> {
			if(rol.getNombre().equals("ROLE_DONADOR")) {
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
		donadorRepositorio.save(donador);
        Authentication newAuth = new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getPassword(),mapearRoles);
        SecurityContextHolder.getContext().setAuthentication(newAuth);
        return true;
	}
}
