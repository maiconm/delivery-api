package com.delivery.api.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "restaurante")
public class Restaurante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 36, nullable = false)
	private String uuid;

	private String nome;

	@Column(name = "taxa_frete")
	private BigDecimal taxaFrete;

	private String cep;

	private String rua;

	private String numero;

	private String bairro;

	private String complemento;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_restaurante")
	private List<Produto> produtos;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pedido")
	private List<Pedido> pedidos;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	@PrePersist
	private void gerarUUID() {
		setUuid(UUID.randomUUID().toString());
	}

}
