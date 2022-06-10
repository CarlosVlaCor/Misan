package com.misan.donacionSangre.seguridad;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.misan.donacionSangre.modelos.Rol;
import com.misan.donacionSangre.modelos.Usuario;
import com.misan.donacionSangre.repositorio.UsuarioRepositorio;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		System.out.println("SDASADSADDSASAD");
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		
		if(usuario == null) {
			throw new UsernameNotFoundException(email);
		}
		
		var roles = new ArrayList<GrantedAuthority>();
		
		for(Rol rol : usuario.getRoles()) {
			roles.add(new SimpleGrantedAuthority(rol.getNombre()));
		}
		return new User(usuario.getEmail(), usuario.getPassword(),roles);
	}

}
