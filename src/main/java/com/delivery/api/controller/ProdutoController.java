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
	
	@Autowired RestauranteService restauranteService;
	
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
		
		return produtoOutputResumidoMapper.mapearCollection(produtos);
		
	}
	
	@GetMapping("/restaurante/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public List<ProdutoOutputResumidoDTO> listarTodosOsProdutosDoRestaurante(@PathVariable Long id) {

		List<Produto> produtos = produtoService.listarPorRestaurante(id);
		
		return produtoOutputResumidoMapper.mapearCollection(produtos);
		
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
	public ProdutoOutputDTO adicionar(@RequestBody @Valid ProdutoInputDTO produtoInputDTO) {
	
		Restaurante restaurante = restauranteService.buscar(produtoInputDTO.getRestaurante());

		Produto produto = produtoIntputMapper.mapearEntity(produtoInputDTO);

		produto.setRestaurante(restaurante);

		produto = produtoService.salvar(produto);
		
		ProdutoOutputDTO produtoOutputDTO = produtoOutputMapper.mapearEntity(produto);
		
		return produtoOutputDTO;
		
	}
	
	@PutMapping("/{id}")
	public ProdutoOutputDTO alterar(@PathVariable Long id, @RequestBody @Valid ProdutoInputDTO produtoInputDTO) {
		
		Produto produto = produtoIntputMapper.mapearEntity(produtoInputDTO);
		
		produto = produtoService.atualizar(id, produto);
		
		ProdutoOutputDTO produtoOutputDTO = produtoOutputMapper.mapearEntity(produto);
		
		return produtoOutputDTO;
		
	}
	
	@PatchMapping("/{id}")
	public ProdutoOutputDTO ajustar(@PathVariable Long id, @RequestBody Map<String, Object> campos) {
		
		Produto produto = produtoService.ajustar(id, campos);
		
		ProdutoOutputDTO produtoOutputDTO = produtoOutputMapper.mapearEntity(produto);
		
		return produtoOutputDTO;
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Produto> deletarProduto(@PathVariable Long id) {
		
		produtoService.excluir(id);
		
		return ResponseEntity.noContent().build();
		
	}
	
}
