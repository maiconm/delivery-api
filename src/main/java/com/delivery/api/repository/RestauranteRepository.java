package com.delivery.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delivery.api.entity.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
	
}
