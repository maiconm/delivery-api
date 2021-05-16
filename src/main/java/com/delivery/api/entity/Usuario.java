package com.delivery.api.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 36, nullable = false)
	private String uuid;
	
	@Column(length = 100, nullable = false)
	private String nome;
	
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "senha", nullable = false)
	private String senha;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_restaurante")
	private Restaurante restaurante;
	
	@PrePersist
	private void gerarUUID() {
		setUuid(UUID.randomUUID().toString());
	}
	
}
