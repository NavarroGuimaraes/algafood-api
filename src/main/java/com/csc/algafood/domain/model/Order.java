package com.csc.algafood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.csc.algafood.domain.model.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="orders")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Order {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "sub_total", nullable=false)
	@JsonProperty(value = "sub_total")
	private BigDecimal subTotal;
	
	@Column(name="delivery_fee", nullable=false)
	@JsonProperty(value = "delivery_fee")
	private BigDecimal deliveryFee;
	
	@Column(nullable=false)
	private BigDecimal total;
	
	@Enumerated
	private OrderStatus status;
	
	@ManyToOne
	@JoinColumn(name="payment_method_id", nullable=false)
	private PaymentMethod paymentMethod;
	
	@ManyToOne
	@JoinColumn(name="restaurant_id", nullable=false)
	private Restaurant restaurant;
	
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	private User user;
	
	@Embedded
	private Address address;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	private LocalDateTime confirmedAt;
	private LocalDateTime canceledAt;
	private LocalDateTime deliveredAt;
	
	@OneToMany(mappedBy="order")
	private List<OrderItem> item = new ArrayList<>();

}
