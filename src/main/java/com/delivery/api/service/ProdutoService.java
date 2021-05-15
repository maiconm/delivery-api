package com.delivery.api.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.delivery.api.entity.Produto;
import com.delivery.api.exception.NotFoundException;
import com.delivery.api.repository.ProdutoRepository;
import com.delivery.api.utils.Utils;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<Produto> listar() {
		return produtoRepository.findAll();
	}
	
	public List<Produto> listarPorRestaurante(Long id) {
		return produtoRepository.selectByRestauranteId(id);
	}
	
	public Produto buscar(Long id) {
		
		Optional<Produto> produto = produtoRepository.findById(id);
		
		if (!produto.isPresent()) {
			
			throw new NotFoundException("Produto não encontrado");
			
		}
		
		return produto.get();
		
	}
	
	public Produto buscarPorUUID(String uuid) {
		
		Produto produto = produtoRepository.selectByUuid(uuid);
		
		if (produto == null) {
			
			throw new NotFoundException("Produto não encontrado");
			
		}
		
		return produto;
		
	}
	
	public Produto salvar(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	public Produto atualizar(Long id, Produto produto) {
		
		Produto produtoAtual = this.buscar(id);
		
		BeanUtils.copyProperties(produto, produtoAtual, "id");
		
		return this.salvar(produtoAtual);
			
	}
	

	
	public Produto ajustar(Long id, Map<String, Object> campos) {
		
		Produto produtoAtual = this.buscar(id);
		
		Utils.merge(produtoAtual, campos);
		
		produtoAtual = this.salvar(produtoAtual);
		
		return produtoAtual;
		
	}
	
	public boolean excluir(Long id) {
		
		try {
			
			produtoRepository.deleteById(id);
			
		} catch (EmptyResultDataAccessException ex) {
			throw new NotFoundException("Produto não encontrado");
		}
		
		return true;
		
	}
	
}