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

import com.delivery.api.dto.ClienteInputDTO;
import com.delivery.api.dto.ClienteOutputDTO;
import com.delivery.api.dto.ClienteOutputResumidoDTO;
import com.delivery.api.entity.Cliente;
import com.delivery.api.mapper.ClienteInputMapper;
import com.delivery.api.mapper.ClienteOutputMapper;
import com.delivery.api.mapper.ClienteOutputResumidoMapper;
import com.delivery.api.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ClienteOutputResumidoMapper clienteOutputResumidoMapper; 
	
	@Autowired
	private ClienteOutputMapper clienteOutputMapper;
	
	@Autowired
	private ClienteInputMapper clienteInputMapper;
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<ClienteOutputResumidoDTO> listarTodosOsClientes() {

		List<Cliente> clientes = clienteService.listar();
		
		return clienteOutputResumidoMapper.mapearCollection(clientes);
		
	}
	
	@GetMapping("/{uuid}")
	@ResponseStatus(code = HttpStatus.OK)
	public ClienteOutputDTO consultaCliente(@PathVariable String uuid) {

		Cliente cliente = clienteService.buscarPorUUID(uuid);
		
		ClienteOutputDTO clienteOutput = clienteOutputMapper.mapearEntity(cliente);
		
		return clienteOutput;
		
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ClienteOutputDTO adicionar(@RequestBody ClienteInputDTO clienteInput) {
		
		Cliente cliente = clienteInputMapper.mapearEntity(clienteInput);
		
		Cliente clienteSalvo = clienteService.salvar(cliente);
		
		ClienteOutputDTO clienteOutput = clienteOutputMapper.mapearEntity(clienteSalvo);
		
		return clienteOutput;		

	}
	
	@PutMapping("/{uuid}")
	public ClienteOutputDTO alterar(@PathVariable String uuid, @RequestBody ClienteInputDTO clienteInput) {
		
		Cliente cliente = clienteInputMapper.mapearEntity(clienteInput);
		
		cliente = clienteService.atualizar(uuid, cliente);
		
		ClienteOutputDTO restauranteOutput = clienteOutputMapper.mapearEntity(cliente);
		
		return restauranteOutput;
		
	}
	
	@PatchMapping("/{uuid}")
	public ClienteOutputDTO ajustar(@PathVariable String uuid, @RequestBody Map<String, Object> campos) {
		
		Cliente cliente = clienteService.ajustar(uuid, campos);
		
		ClienteOutputDTO clienteOutput = clienteOutputMapper.mapearEntity(cliente);
		
		return clienteOutput;
		
	}
	
	@DeleteMapping("/{uuid}")
	public ResponseEntity<ClienteOutputDTO> deletarCliente(@PathVariable String uuid) {
		
		clienteService.excluir(uuid);
		
		return ResponseEntity.noContent().build();
		
	}	
	
}
