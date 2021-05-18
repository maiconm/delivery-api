package com.delivery.api.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.delivery.api.entity.Restaurante;
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
	
	public Restaurante buscar(Long id) {
		
		Optional<Restaurante> restaurante = restauranteRepository.findById(id);
		
		if (!restaurante.isPresent()) {
			
			throw new NotFoundException("Restaurante não encontrado");
			
		}
		
		return restaurante.get();
		
	}
	
	public Restaurante salvar(Restaurante restaurante) {
		return restauranteRepository.save(restaurante);
	}
	
	public Restaurante atualizar(Long id, Restaurante restaurante) {
		
		Restaurante restauranteAtual = this.buscar(id);
			
		BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "produtos", "usuario");
		
		return this.salvar(restauranteAtual);
			
	}
	

	
	public Restaurante ajustar(Long id, Map<String, Object> campos) {
		
		Restaurante restauranteAtual = this.buscar(id);
		
		Utils.merge(restauranteAtual, campos);
		
		restauranteAtual = this.salvar(restauranteAtual);
		
		return restauranteAtual;
		
	}
	
	public boolean excluir(Long id) {
		
		try {
			
			restauranteRepository.deleteById(id);
			
		} catch (EmptyResultDataAccessException ex) {
			throw new NotFoundException("Restaurante não encontrado");
		}
		
		return true;
		
	}
	
}
