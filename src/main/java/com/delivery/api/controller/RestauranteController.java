package com.delivery.api.controller;

import java.util.List;
import java.util.Map;

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

import com.delivery.api.dto.RestauranteOutputDTO;
import com.delivery.api.dto.RestauranteOutputResumidoDTO;
import com.delivery.api.entity.Restaurante;
import com.delivery.api.mapper.RestauranteOutputMapper;
import com.delivery.api.mapper.RestauranteOutputResumidoMapper;
import com.delivery.api.service.RestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
	
	@Autowired
	private RestauranteOutputMapper restauranteOutputMapper;
	
	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private RestauranteOutputResumidoMapper restauranteOutputResumidoMapper; 
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<RestauranteOutputResumidoDTO> listarTodosOsRestaurantes() {

		List<Restaurante> restaurantes = restauranteService.listar();
		
		return restauranteOutputResumidoMapper.mapearCollection(restaurantes);
		
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public RestauranteOutputDTO consultaRestaurante(@PathVariable Long id) {

		Restaurante restaurante = restauranteService.buscar(id);
		
		RestauranteOutputDTO restauranteOutput = restauranteOutputMapper.mapearEntity(restaurante);
		
		return restauranteOutput;
		
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Restaurante adicionar(@RequestBody Restaurante restaurante) {
		
		return restauranteService.salvar(restaurante);
		
	}
	
	@PutMapping("/{id}")
	public Restaurante alterar(@PathVariable Long id, @RequestBody Restaurante restaurante) {
		
		restaurante = restauranteService.atualizar(id, restaurante);
		
		return restaurante;
		
	}
	
	@PatchMapping("/{id}")
	public Restaurante ajustar(@PathVariable Long id, @RequestBody Map<String, Object> campos) {
		
		Restaurante restaurante = restauranteService.ajustar(id, campos);
		
		return restaurante;
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Restaurante> deletarRestaurante(@PathVariable Long id) {
		
		restauranteService.excluir(id);
		
		return ResponseEntity.noContent().build();
		
	}	
	
}
