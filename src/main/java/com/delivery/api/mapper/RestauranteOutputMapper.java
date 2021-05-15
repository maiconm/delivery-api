package com.delivery.api.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.delivery.api.dto.RestauranteOutputDTO;
import com.delivery.api.entity.Restaurante;

@Component
public class RestauranteOutputMapper {

	@Autowired
	private ModelMapper modelMapper;

	public RestauranteOutputDTO mapearEntity(Restaurante restaurante) {
		return modelMapper.map(restaurante, RestauranteOutputDTO.class);
	}
}
