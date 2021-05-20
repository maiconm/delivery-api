package com.delivery.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.delivery.api.entity.Pedido;
import com.delivery.api.exception.ConflictException;
import com.delivery.api.exception.NotFoundException;
import com.delivery.api.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	public List<Pedido> listar() {
		
		return pedidoRepository.findAll();
		
	}
	
	public List<Pedido> listarPorRestaurante(String uuid) {
		
		return pedidoRepository.selectByRestaurante(uuid);
		
	}
	
	public List<Pedido> listarPorCliente(String uuid) {
		
		return pedidoRepository.selectByCliente(uuid);
		
	}
	
	public Pedido buscar(Long id) {
		
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		
		if (!pedido.isPresent()) {
			
			throw new NotFoundException("Pedido não encontrado!");
			
		}
		
		return pedido.get();
		
	}
	
	public Pedido buscarPorUUID(String uuid) {
		
		Pedido pedido = pedidoRepository.selectByUuid(uuid);
		
		if (pedido == null) {
			
			throw new NotFoundException("Pedido não encontrado!");
			
		}
		
		return pedido;
		
	}
	
	public Pedido salvar(Pedido pedido) {
		
		return pedidoRepository.save(pedido);
		
	}
	
	public boolean excluir(String uuid) {
		
		try {
			
			pedidoRepository.deleteByUuid(uuid);
			
		} catch (Exception ex) {
			
			if (ex.getClass().equals(EmptyResultDataAccessException.class)) {
			
				throw new NotFoundException("Pedido não encontrado!");
			
			} else {
				
				throw new ConflictException("Precisa deletar os produtos antes!");
				
			}
			
		}
		
		return true;
		
	}
	
}
