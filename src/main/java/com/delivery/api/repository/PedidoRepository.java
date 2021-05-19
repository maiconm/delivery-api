package com.delivery.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.delivery.api.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
	public Pedido findByUuid(String uuid);
	
	@Query("select ped from Pedido ped where ped.uuid = :uuid")
	public Pedido selectByUuid(@Param("uuid") String uuid);
	
	@Query("select ped from Pedido ped where ped.cliente.uuid= :uuid")
	public List<Pedido> selectByUsuario(@Param("uuid") String uuid);
	
	@Query("select ped from Pedido ped where ped.restaurante.uuid= :uuid")
	public List<Pedido> selectByRestaurante(@Param("uuid") String uuid);
	
	@Transactional
	@Modifying
	@Query("delete from Pedido ped where ped.uuid = :uuid")
	public void deleteByUuid(@Param("uuid") String uuid);
	
}
