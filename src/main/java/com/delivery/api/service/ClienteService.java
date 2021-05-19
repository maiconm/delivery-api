package com.delivery.api.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.delivery.api.entity.Cliente;
import com.delivery.api.exception.ConflictException;
import com.delivery.api.exception.NotFoundException;
import com.delivery.api.repository.ClienteRepository;
import com.delivery.api.utils.Utils;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}
	
	// TODO: listar por Restaurante
//	public List<Restaurante> listarPorUsuario(String uuid) {
//		return clienteRepository.selectByRestaurante(uuid);
//	}
	
	public Cliente buscar(Long id) {
		
		Optional<Cliente> cliente = clienteRepository.findById(id);
		
		if (!cliente.isPresent()) {
			
			throw new NotFoundException("Cliente não encontrado");
			
		}
		
		return cliente.get();
		
	}
	
	public Cliente buscarPorUUID(String uuid) {
		
		Cliente cliente = clienteRepository.selectByUuid(uuid);
		
		if (cliente == null) {
			
			throw new NotFoundException("Cliente não encontrado");
			
		}
		
		return cliente;
		
	}
	
	public Cliente salvar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public Cliente atualizar(String uuid, Cliente cliente) {
		
		Cliente clienteAtual = this.buscarPorUUID(uuid);
			
		BeanUtils.copyProperties(cliente, clienteAtual, "uuid", "id");
		
		return this.salvar(clienteAtual);
			
	}
	

	
	public Cliente ajustar(String uuid, Map<String, Object> campos) {
		
		Cliente clienteAtual = this.buscarPorUUID(uuid);
		
		Utils.merge(clienteAtual, campos);
		
		clienteAtual = this.salvar(clienteAtual);
		
		return clienteAtual;
		
	}
	
	public boolean excluir(String uuid) {
		
		try {
			
			clienteRepository.deleteByUuid(uuid);
			
		} catch (Exception ex) {
			
			if (ex.getClass().equals(EmptyResultDataAccessException.class)) {
			
				throw new NotFoundException("Cliente não encontrado");
			
			} else {
				
				throw new ConflictException("Precisa deletar os pedidos antes!");
				
			}
			
		}
		
		return true;
		
	}
	
}
