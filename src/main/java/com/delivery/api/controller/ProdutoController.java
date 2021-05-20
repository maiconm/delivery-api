package com.delivery.api.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.api.dto.ProdutoInputDTO;
import com.delivery.api.dto.ProdutoOutputDTO;
import com.delivery.api.dto.ProdutoOutputResumidoDTO;
import com.delivery.api.entity.Produto;
import com.delivery.api.entity.Restaurante;
import com.delivery.api.mapper.ProdutoInputMapper;
import com.delivery.api.mapper.ProdutoOutputMapper;
import com.delivery.api.mapper.ProdutoOutputResumidoMapper;
import com.delivery.api.service.ProdutoService;
import com.delivery.api.service.RestauranteService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired 
	private RestauranteService restauranteService;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ProdutoOutputResumidoMapper produtoOutputResumidoMapper;
	
	@Autowired
	private ProdutoOutputMapper produtoOutputMapper;
	
	@Autowired
	private ProdutoInputMapper produtoIntputMapper;
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<ProdutoOutputResumidoDTO> listarTodosOsProdutos() {

		List<Produto> produtos = produtoService.listar();
		
		List<ProdutoOutputResumidoDTO> produtosOutput = produtoOutputResumidoMapper.mapearCollection(produtos);
		
		return produtosOutput;
		
	}
	
	@GetMapping("/restaurante/{uuid}")
	@ResponseStatus(code = HttpStatus.OK)
	public List<ProdutoOutputResumidoDTO> listarTodosOsProdutosDoRestaurante(@PathVariable String uuid) {

		List<Produto> produtos = produtoService.listarPorRestaurante(uuid);
		
		List<ProdutoOutputResumidoDTO> produtosOutput = produtoOutputResumidoMapper.mapearCollection(produtos);
		
		return produtosOutput;
		
	}
	
	@GetMapping("/{uuid}")
	@ResponseStatus(code = HttpStatus.OK)
	public ProdutoOutputDTO consultaProduto(@PathVariable String uuid) {

		Produto produto = produtoService.buscarPorUUID(uuid);
		
		
		ProdutoOutputDTO produtoOutputDTO = produtoOutputMapper.mapearEntity(produto);
		
		return produtoOutputDTO;
		
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ProdutoOutputDTO adicionar(@RequestBody @Valid ProdutoInputDTO produtoInput) {
	
		Restaurante restaurante = restauranteService.buscarPorUUID(produtoInput.getRestaurante());

		Produto produto = produtoIntputMapper.mapearEntity(produtoInput);

		produto.setRestaurante(restaurante);

		produto = produtoService.salvar(produto);
		
		ProdutoOutputDTO produtoOutputDTO = produtoOutputMapper.mapearEntity(produto);
		
		return produtoOutputDTO;
		
	}
	
	@PutMapping("/{uuid}")
	public ProdutoOutputDTO alterar(@PathVariable String uuid, @RequestBody @Valid ProdutoInputDTO produtoInput) {
		
		Produto produto = produtoIntputMapper.mapearEntity(produtoInput);
		
		produto = produtoService.atualizar(uuid, produto);
		
		ProdutoOutputDTO produtoOutputDTO = produtoOutputMapper.mapearEntity(produto);
		
		return produtoOutputDTO;
		
	}
	
	@PatchMapping("/{uuid}")
	public ProdutoOutputDTO ajustar(@PathVariable String uuid, @RequestBody Map<String, Object> campos) {
		
		Produto produto = produtoService.ajustar(uuid, campos);
		
		ProdutoOutputDTO produtoOutputDTO = produtoOutputMapper.mapearEntity(produto);
		
		return produtoOutputDTO;
		
	}
	
	@DeleteMapping("/{uuid}")
	public ResponseEntity<Produto> deletarProduto(@PathVariable String uuid) {
		
		produtoService.excluir(uuid);
		
		return ResponseEntity.noContent().build();

	}
	
}
