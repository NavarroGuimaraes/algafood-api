package com.csc.algafood.infrastructure.specifications.restaurants;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.csc.algafood.domain.model.Restaurant;

public class RestaurantSpecs {

	public static Specification<Restaurant> freeDeliveryFee(){
		return (root, query, builder) -> 
			builder.equal(root.get("deliveryFee"), BigDecimal.ZERO);
		
	}
	
	public static Specification<Restaurant> nameLike(String name) {
		return (root, query, builder) -> builder.like(root.get("name"), "%"+name+"%");
	}
	
}
