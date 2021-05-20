package com.delivery.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TesteController {
	
	@GetMapping("/hello")
	@ResponseBody
	public String hello() {
		
		return "Hello world!!";
		
	}

}
