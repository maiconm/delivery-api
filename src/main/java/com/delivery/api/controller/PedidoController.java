package com.delivery.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.api.dto.PedidoInputDTO;
import com.delivery.api.dto.PedidoOutputDTO;
import com.delivery.api.dto.PedidoOutputResumidoDTO;
import com.delivery.api.dto.RestauranteOutputDTO;
import com.delivery.api.entity.Cliente;
import com.delivery.api.entity.Pedido;
import com.delivery.api.entity.Produto;
import com.delivery.api.entity.Restaurante;
import com.delivery.api.mapper.PedidoInputMapper;
import com.delivery.api.mapper.PedidoOutputMapper;
import com.delivery.api.mapper.PedidoOutputResumidoMapper;
import com.delivery.api.service.ClienteService;
import com.delivery.api.service.PedidoService;
import com.delivery.api.service.ProdutoService;
import com.delivery.api.service.RestauranteService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired 
	private PedidoInputMapper pedidoInputMapper;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private PedidoOutputMapper pedidoOutputMapper;
	
	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired 
	private PedidoService pedidoService;
	
	@Autowired
	private PedidoOutputResumidoMapper pedidoOutputResumidoMapper; 
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<PedidoOutputResumidoDTO> listarTodosOsPedidos() {

		List<Pedido> pedidos = pedidoService.listar();
		
		return pedidoOutputResumidoMapper.mapearCollection(pedidos);
		
	}
	
	@GetMapping("/restaurante/{uuid}")
	@ResponseStatus(code = HttpStatus.OK)
	public List<PedidoOutputDTO> consultarPorRestaurante(@PathVariable String uuid) {
		
		List<Pedido> pedidos = pedidoService.listarPorRestaurante(uuid);
		
		List<PedidoOutputDTO> pedidossOutput = pedidoOutputMapper.mapearCollection(pedidos);
		
		return pedidossOutput;
		
	}
	
	@GetMapping("/cliente/{uuid}")
	@ResponseStatus(code = HttpStatus.OK)
	public List<PedidoOutputDTO> consultarPorCliente(@PathVariable String uuid) {
		
		List<Pedido> pedidos = pedidoService.listarPorCliente(uuid);
		
		List<PedidoOutputDTO> pedidossOutput = pedidoOutputMapper.mapearCollection(pedidos);
		
		return pedidossOutput;
		
	}
	
	@GetMapping("/{uuid}")
	@ResponseStatus(code = HttpStatus.OK)
	public PedidoOutputDTO consultarPedido(@PathVariable String uuid) {

		Pedido pedido = pedidoService.buscarPorUUID(uuid);
		
		PedidoOutputDTO pedidoOutput = pedidoOutputMapper.mapearEntity(pedido);
		
		return pedidoOutput;
		
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public PedidoOutputDTO adicionar(@RequestBody PedidoInputDTO pedidoInput) {
		
		Cliente cliente = clienteService.buscarPorUUID(pedidoInput.getCliente());
		
		List<Produto> produtos = pedidoInput.getProdutos()
				.stream()
				.map(protudoUuid -> produtoService.buscarPorUUID(protudoUuid))
				.collect(Collectors.toList());
		
		Restaurante restaurante = restauranteService.buscarPorUUID(pedidoInput.getRestaurante());
		
		Pedido pedido = pedidoInputMapper.mapearEntity(pedidoInput);
		
		pedido.setCliente(cliente);
		
		pedido.setProdutos(produtos);
		
		pedido.setRestaurante(restaurante);
		
		pedidoService.salvar(pedido);
		
		PedidoOutputDTO pedidoOutput = pedidoOutputMapper.mapearEntity(pedido);
		
		return pedidoOutput;

	}
	
	@DeleteMapping("/{uuid}")
	public ResponseEntity<RestauranteOutputDTO> deletarPedido(@PathVariable String uuid) {
		
		pedidoService.excluir(uuid);
		
		return ResponseEntity.noContent().build();
		
	}	
	
}
