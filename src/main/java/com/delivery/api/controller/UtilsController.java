package com.delivery.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.api.dto.EnderecoDTO;
import com.delivery.api.service.CEPService;

@RestController
@RequestMapping("/utils")
public class UtilsController {

	@Autowired
	private CEPService cepService;
	
	@GetMapping("/cep/{cep}")
	@ResponseStatus(code = HttpStatus.OK)
	public EnderecoDTO consultarCEP(@PathVariable String cep) {
		
		return cepService.consultarCEP(cep);
		
	}
	
}
