package com.delivery.api.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

@Getter
public class AuthUser extends User {
	 
	private static final long serialVersionUID = 1L;
	
	private String nome;
	
	private String uuid;
	
	public AuthUser(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
		
		super(usuario.getEmail(), usuario.getSenha(), authorities);
		
		this.nome = usuario.getNome();
		
		this.uuid = usuario.getUuid();
		
	}
	
}
