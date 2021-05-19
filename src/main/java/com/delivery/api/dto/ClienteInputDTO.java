package com.delivery.api.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ClienteInputDTO {
	
	@NotBlank
	private String nome;

	@NotBlank
	private String cep;
	
	@NotBlank 
	private String contato;

	private String rua;

	private String numero;

	private String bairro;

	private String complemento;

}
