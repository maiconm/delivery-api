package com.delivery.api.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class PedidoInputDTO {
	
	@NotBlank
	private String restaurante;

	@NotBlank
	private List<String> produtos;
	
	@NotBlank 
	private String cliente;

}
