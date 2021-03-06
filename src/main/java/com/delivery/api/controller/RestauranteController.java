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
import com.delivery.api.service.RestauranteService;
import com.delivery.api.service.UsuarioService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
	
	@Autowired
	private UsuarioService usuarioService;
	
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
	
	@GetMapping("/usuario/{email}")
	@ResponseStatus(code = HttpStatus.OK)
	public List<RestauranteOutputDTO> consultarPorUsuario(@PathVariable String email) {
		
		List<Restaurante> restaurantes = restauranteService.listarPorUsuario(email);
		
		List<RestauranteOutputDTO> restaurantesOutput = restauranteOutputMapper.mapearCollection(restaurantes);
		
		return restaurantesOutput;
		
	}
	
	@GetMapping("/{uuid}")
	@ResponseStatus(code = HttpStatus.OK)
	public RestauranteOutputDTO consultaRestaurante(@PathVariable String uuid) {

		Restaurante restaurante = restauranteService.buscarPorUUID(uuid);
		
		RestauranteOutputDTO restauranteOutput = restauranteOutputMapper.mapearEntity(restaurante);
		
		return restauranteOutput;
		
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public RestauranteOutputDTO adicionar(@RequestBody RestauranteInputDTO restauranteInput) {
		
		Usuario usuario = usuarioService.getUsuarioByEmail(restauranteInput.getUsuario());
		
		Restaurante restaurante = restauranteInputMapper.mapearEntity(restauranteInput);
			
		restaurante.setUsuario(usuario);
		
		Restaurante restauranteSalvo = restauranteService.salvar(restaurante);
		
		RestauranteOutputDTO restauranteOutput = restauranteOutputMapper.mapearEntity(restauranteSalvo);
		
		return restauranteOutput;		

	}
	
	@PutMapping("/{uuid}")
	public RestauranteOutputDTO alterar(@PathVariable String uuid, @RequestBody RestauranteInputDTO restauranteInput) {
		
		Restaurante restaurante = restauranteInputMapper.mapearEntity(restauranteInput);
		
		restaurante = restauranteService.atualizar(uuid, restaurante);
		
		RestauranteOutputDTO restauranteOutput = restauranteOutputMapper.mapearEntity(restaurante);
		
		return restauranteOutput;
		
	}
	
	@PatchMapping("/{uuid}")
	public RestauranteOutputDTO ajustar(@PathVariable String uuid, @RequestBody Map<String, Object> campos) {
		
		Restaurante restaurante = restauranteService.ajustar(uuid, campos);
		
		RestauranteOutputDTO restauranteOutput = restauranteOutputMapper.mapearEntity(restaurante);
		
		return restauranteOutput;
		
	}
	
	@DeleteMapping("/{uuid}")
	public ResponseEntity<RestauranteOutputDTO> deletarRestaurante(@PathVariable String uuid) {
		
		restauranteService.excluir(uuid);
		
		return ResponseEntity.noContent().build();
		
	}	
	
}
