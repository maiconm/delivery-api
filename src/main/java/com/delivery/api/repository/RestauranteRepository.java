package com.delivery.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.delivery.api.entity.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
	
	public Restaurante findByUuid(String uuid);
	
	@Query("select res from Restaurante res where res.uuid = :uuid")
	public Restaurante selectByUuid(@Param("uuid") String uuid);
	
	@Query("select res from Restaurante res where res.usuario.email = :email")
	public List<Restaurante> selectByUsuarioEmail(@Param("email") String email);
	
	@Transactional
	@Modifying
	@Query("delete from Restaurante res where res.uuid = :uuid")
	public void deleteByUuid(@Param("uuid") String uuid);
	
}
