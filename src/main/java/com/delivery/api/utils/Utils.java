package com.delivery.api.utils;

import java.lang.reflect.Field;
import java.util.Map;

import org.springframework.util.ReflectionUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
	
	public static void merge(Object original, Map<String, Object> campos) {
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
		
		objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
		
		Object origem = objectMapper.convertValue(campos, original.getClass());
		
		campos.forEach((propriedade, valor) -> {
			
			Field campo = ReflectionUtils.findField(original.getClass(), propriedade);
			
			campo.setAccessible(true);
			
			Object novoValor = ReflectionUtils.getField(campo, origem);
			
			ReflectionUtils.setField(campo, original, novoValor);
			
		});
		
	}
	
}
