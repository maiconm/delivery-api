package com.delivery.api.dto;

import lombok.Data;

@Data
public class ClienteOutputDTO {
	
	private String uuid;
	
	private String nome;

	private String cep;
	 
	private String contato;

	private String rua;

	private String numero;

	private String bairro;

	private String complemento;

}
