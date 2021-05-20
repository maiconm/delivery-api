package com.delivery.api.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pedido")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 36, nullable = false)
	private String uuid;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pedido")
	private Restaurante restaurante;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_produto")
	private List<Produto> produtos;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;
	
	@Column(name = "preco_total")
	private BigDecimal precoTotal;
	
	@PrePersist
	private void gerarUUIDCalcularPrecoTotal() {
		
		setUuid(UUID.randomUUID().toString());
		
		calcularPrecoTotal();
		
	}
	
	private void calcularPrecoTotal() {
		
		BigDecimal somaPrecoTotal = BigDecimal.ZERO;
		
		for (Produto produto : produtos) {
			
			somaPrecoTotal = somaPrecoTotal.add(produto.getPreco());
			
		}
		
		somaPrecoTotal = somaPrecoTotal.add(restaurante.getTaxaFrete());
		
		setPrecoTotal(somaPrecoTotal);
		
	}

}
