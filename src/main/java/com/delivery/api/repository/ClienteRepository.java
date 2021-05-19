package com.delivery.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.delivery.api.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	public Cliente findByUuid(String uuid);
	
	@Query("select cli from Cliente cli where cli.uuid = :uuid")
	public Cliente selectByUuid(@Param("uuid") String uuid);
	
	@Transactional
	@Modifying
	@Query("delete from Cliente cli where cli.uuid = :uuid")
	public void deleteByUuid(@Param("uuid") String uuid);
	
}
