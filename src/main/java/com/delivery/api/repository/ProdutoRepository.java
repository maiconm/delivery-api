package com.delivery.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.delivery.api.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	public Produto findByUuid(String uuid);
	
	@Query("select pro from Produto pro where pro.uuid = :uuid")
	public Produto selectByUuid(@Param("uuid") String uuid);
	
	@Query("select pro from Produto pro where pro.restaurante.id = :id")
	public List<Produto> selectByRestauranteId(@Param("id") Long id);
	
	@Transactional
	@Modifying
	@Query("delete from Produto pro where pro.uuid = :uuid")
	public void deleteByUuid(@Param("uuid") String uuid);
	
}
