package com.delivery.api.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "restaurante")
public class Restaurante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	@Column(name = "taxa_frete")
	private BigDecimal taxaFrete;

	private String cep;

	private String rua;

	private String numero;

	private String bairro;

	private String complemento;

	@OneToMany
	@JoinColumn(name = "id_restaurante")
	private List<Produto> produtos;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

}
