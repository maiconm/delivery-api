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

import com.delivery.api.dto.RestauranteInputDTO;
import com.delivery.api.dto.RestauranteOutputDTO;
import com.delivery.api.dto.RestauranteOutputResumidoDTO;
import com.delivery.api.entity.Restaurante;
import com.delivery.api.entity.Usuario;
import com.delivery.api.mapper.RestauranteInputMapper;
import com.delivery.api.mapper.RestauranteOutputMapper;
import com.delivery.api.mapper.RestauranteOutputResumidoMapper;
import com.delivery.api.service.JpaUserDetailService;
import com.delivery.api.service.RestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
	
	@Autowired
	private JpaUserDetailService jpaUserDetailService;
	
	@Autowired
	private RestauranteInputMapper restauranteInputMapper;
	
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
	public RestauranteOutputDTO adicionar(@RequestBody RestauranteInputDTO restauranteInput) {
		
		Usuario usuario = jpaUserDetailService.getUsuarioByEmail(restauranteInput.getUsuario());
		
		Restaurante restaurante = restauranteInputMapper.mapearEntity(restauranteInput);
		
		restaurante.setUsuario(usuario);
		
		Restaurante restauranteSalvo = restauranteService.salvar(restaurante);
		
		return restauranteOutputMapper.mapearEntity(restauranteSalvo);
		
	}
	
	@PutMapping("/{id}")
	public RestauranteOutputDTO alterar(@PathVariable Long id, @RequestBody RestauranteInputDTO restauranteInput) {
		
		Restaurante restaurante = restauranteInputMapper.mapearEntity(restauranteInput);
		
		restaurante = restauranteService.atualizar(id, restaurante);
		
		RestauranteOutputDTO restauranteOutput = restauranteOutputMapper.mapearEntity(restaurante);
		
		return restauranteOutput;
		
	}
	
	@PatchMapping("/{id}")
	public RestauranteOutputDTO ajustar(@PathVariable Long id, @RequestBody Map<String, Object> campos) {
		
		Restaurante restaurante = restauranteService.ajustar(id, campos);
		
		RestauranteOutputDTO restauranteOutput = restauranteOutputMapper.mapearEntity(restaurante);
		
		return restauranteOutput;
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<RestauranteOutputDTO> deletarRestaurante(@PathVariable Long id) {
		
		restauranteService.excluir(id);
		
		return ResponseEntity.noContent().build();
		
	}	
	
}
