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

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="products")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false)
	private BigDecimal price;
	
	@Column(name="is_active", nullable = false)
	private boolean isActive = true;
	
	@ManyToOne
	@JoinColumn(name = "restaurant_id", nullable=false)
	private Restaurant restaurant;
	
	
}
