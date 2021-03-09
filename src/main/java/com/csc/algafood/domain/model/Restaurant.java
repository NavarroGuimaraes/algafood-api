package com.csc.algafood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name="restaurants")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name", nullable = false)
	private String name;
	
	@Column(name="delivery_fee", nullable = false)
	private BigDecimal deliveryFee;
	
	@JsonIgnore
	@CreationTimestamp
	@Column(name="created_at", nullable=false, columnDefinition = "datetime")
	private LocalDateTime createdAt;
	
	@JsonIgnore
	@UpdateTimestamp
	@Column(name="updated_at", nullable=false, columnDefinition = "datetime")
	private LocalDateTime updatedAt;
	
//	@JsonIgnoreProperties("hibernateLazyInitializer")
//	@ManyToOne(fetch = FetchType.LAZY)
	@ManyToOne
	@JoinColumn(name="cookery_id")
	private Cookery cookery;
	
	@Embedded
	@JsonIgnore
	private Address address;
	
	@JsonIgnore
	@OneToMany(mappedBy="restaurant")
	private List<Product> products = new ArrayList<>();
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name="restaurant_payment_methods",
			joinColumns = @JoinColumn(name="restaurant_id"),
			inverseJoinColumns = @JoinColumn(name="payment_method_id"))
	private List<PaymentMethod> paymentMethods = new ArrayList<>();
	
}
