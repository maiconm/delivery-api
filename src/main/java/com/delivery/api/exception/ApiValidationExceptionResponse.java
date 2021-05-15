package com.delivery.api.exception;

import java.util.List;

import lombok.Data;

@Data
public class ApiValidationExceptionResponse {

	private Integer status;
	
	private String mensagem;
	
	private List<Erro> validacoes;
	
	@Data
	public static class Erro {
		
		private String campo;
		
		private String erro;
		
	}
	
}
