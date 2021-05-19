package com.delivery.api.dto;

import java.util.List;

import lombok.Data;

@Data
public class PedidoOutputDTO {
	
	private String uuid;
	
	private RestauranteOutputResumidoDTO restaurante;

	private List<ProdutoOutputResumidoDTO> produtos;
	 
	private ClienteOutputResumidoDTO cliente;
	
	private String precoTotal;

}
