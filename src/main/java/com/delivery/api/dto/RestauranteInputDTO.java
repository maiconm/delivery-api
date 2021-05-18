package com.delivery.api.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class RestauranteInputDTO {
	
	private String nome;

	private BigDecimal taxaFrete;

	private String cep;

	private String rua;

	private String numero;

	private String bairro;

	private String complemento;
	
	private String usuario;

}
