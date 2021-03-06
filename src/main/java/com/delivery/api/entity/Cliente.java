package com.delivery.api.entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 36, nullable = false)
	private String uuid;
	
	@Column(length = 100, nullable = false)
	private String nome;
	
	@Column(name = "contato", nullable = false)
	private String contato;
	
	private String cep;

	private String rua;

	private String numero;

	private String bairro;

	private String complemento;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cliente")
	private List<Pedido> pedidos;
	
	@PrePersist
	private void gerarUUID() {
		setUuid(UUID.randomUUID().toString());
	}
	
}
