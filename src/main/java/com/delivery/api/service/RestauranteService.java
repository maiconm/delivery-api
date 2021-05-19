package com.delivery.api.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.delivery.api.entity.Restaurante;
import com.delivery.api.exception.ConflictException;
import com.delivery.api.exception.NotFoundException;
import com.delivery.api.repository.RestauranteRepository;
import com.delivery.api.utils.Utils;

@Service
public class RestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	public List<Restaurante> listar() {
		return restauranteRepository.findAll();
	}
	
	public List<Restaurante> listarPorUsuario(String email) {
		return restauranteRepository.selectByUsuarioEmail(email);
	}
	
	public Restaurante buscar(Long id) {
		
		Optional<Restaurante> restaurante = restauranteRepository.findById(id);
		
		if (!restaurante.isPresent()) {
			
			throw new NotFoundException("Restaurante não encontrado");
			
		}
		
		return restaurante.get();
		
	}
	
	public Restaurante buscarPorUUID(String uuid) {
		
		Restaurante restaurante = restauranteRepository.selectByUuid(uuid);
		
		if (restaurante == null) {
			
			throw new NotFoundException("Produto não encontrado");
			
		}
		
		return restaurante;
		
	}
	
	public Restaurante salvar(Restaurante restaurante) {
		return restauranteRepository.save(restaurante);
	}
	
	public Restaurante atualizar(String uuid, Restaurante restaurante) {
		
		Restaurante restauranteAtual = this.buscarPorUUID(uuid);
			
		BeanUtils.copyProperties(restaurante, restauranteAtual, "uuid", "id", "produtos", "usuario");
		
		return this.salvar(restauranteAtual);
			
	}
	

	
	public Restaurante ajustar(String uuid, Map<String, Object> campos) {
		
		Restaurante restauranteAtual = this.buscarPorUUID(uuid);
		
		Utils.merge(restauranteAtual, campos);
		
		restauranteAtual = this.salvar(restauranteAtual);
		
		return restauranteAtual;
		
	}
	
	public boolean excluir(String uuid) {
		
		try {
			
			restauranteRepository.deleteByUuid(uuid);
			
		} catch (Exception ex) {
			
			if (ex.getClass().equals(EmptyResultDataAccessException.class)) {
			
				throw new NotFoundException("Restaurante não encontrado");
			
			} else {
				
				throw new ConflictException("Precisa deletar os produtos antes!");
				
			}
			
		}
		
		return true;
		
	}
	
}
