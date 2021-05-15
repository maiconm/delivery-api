package com.delivery.api.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class RestauranteOutputDTO {

	private Long id;

	private String nome;
	
	private BigDecimal taxaFrete;
	
	private String cep;
	
	private String rua;
	
	private String numero;
	
	private String bairro;
	
	private String complemento;
	
	private List<ProdutoOutputResumidoDTO>produtos;
}
