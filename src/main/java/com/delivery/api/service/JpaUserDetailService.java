package com.delivery.api.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.delivery.api.entity.AuthUser;
import com.delivery.api.entity.Usuario;
import com.delivery.api.exception.NotFoundException;
import com.delivery.api.repository.UsuarioRepository;

@Service
public class JpaUserDetailService implements UserDetailsService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioRepository.selectByEmail(username);
		
		if (usuario == null) {
			throw new NotFoundException("Usuário não encontrado!");
		}
		
		return new AuthUser(usuario, Collections.emptyList());
		
	}

}
