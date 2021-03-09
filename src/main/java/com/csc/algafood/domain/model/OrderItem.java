package com.csc.algafood.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="order_items")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrderItem {
	
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name="order_id")
	private Order order;
	
	@Column(nullable=false)
	private BigDecimal amount;
	
	@Column(name="unit_price", nullable=false)
	@JsonProperty(value="unit_price")
	private BigDecimal unitPrice;
	
	@Column(name="total_price")
	@JsonProperty(value="total_price")
	private BigDecimal totalPrice;
	
	private String observation;

}
