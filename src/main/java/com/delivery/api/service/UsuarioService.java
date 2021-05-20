package com.delivery.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delivery.api.entity.Usuario;
import com.delivery.api.exception.NotFoundException;
import com.delivery.api.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	public Usuario getUsuarioByEmail(String email) {
		
		Usuario usuario = usuarioRepository.selectByEmail(email);
		
		if (usuario == null) {
			
			throw new NotFoundException("Usuário não encontrado!");
			
		}
		
		return usuario;
	}

}
