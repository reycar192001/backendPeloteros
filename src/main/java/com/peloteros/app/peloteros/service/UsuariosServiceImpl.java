package com.peloteros.app.peloteros.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.peloteros.app.peloteros.entity.Usuarios;
import com.peloteros.app.peloteros.repository.UsuariosRepository;

@Service
public class UsuariosServiceImpl implements UsuariosService,UserDetailsService{
	
	
	@Autowired
	private UsuariosRepository repository;

	@Override
	@Transactional
	public void insert(Usuarios usuarios) {
		repository.save(usuarios);
	}

	@Override
	@Transactional
	public void update(Usuarios usuarios) {
		repository.save(usuarios);
	}

	@Override
	@Transactional
	public void delete(Integer UsuarioID) {
		repository.deleteById(UsuarioID);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuarios findById(Integer UsuarioID) {
		return repository.findById(UsuarioID).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Usuarios> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public int isExistNombre(String Nombre) {
		return repository.isExistNombre(Nombre);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Usuarios findByUsername(String Correo) {
		return repository.findByUsername(Correo);
	}
	
	@Override
	public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
	    Usuarios usuarioDb = this.findByUsername(correo);
	    if (usuarioDb != null) {
	        Collection<GrantedAuthority> authorities = new ArrayList<>();
	        authorities.add(new SimpleGrantedAuthority("ROLE_" + usuarioDb.getRolesObj().getNombre()));

	        return new User(usuarioDb.getCorreo(), usuarioDb.getPassword(), authorities);
	    }
	    throw new UsernameNotFoundException("¡Error, Username no existe en la base de datos!");
	}
	
	
	
}
