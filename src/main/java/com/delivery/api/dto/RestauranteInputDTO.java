package com.delivery.api.dto;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class RestauranteInputDTO {
	
	@NotBlank
	private String nome;

	@NotNull
	@DecimalMin(value = "0.00", inclusive = false)
	@Digits(integer = 15, fraction = 2)
	private BigDecimal taxaFrete;

	@NotBlank
	private String cep;

	private String rua;

	private String numero;

	private String bairro;

	private String complemento;
	
	@NotNull
	private String usuario;

}
