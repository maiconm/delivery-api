package com.delivery.api.dto;

import java.util.List;

import lombok.Data;

@Data
public class PedidoOutputResumidoDTO {
	
	private String uuid;

	private List<ProdutoOutputResumidoDTO> produtos;
	 
	private ClienteOutputResumidoDTO cliente;
	
	private String precoTotal;

}
