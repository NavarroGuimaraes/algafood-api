package com.csc.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import com.csc.algafood.domain.model.Restaurant;

public interface RestaurantRepositoryCustom {

	List<Restaurant> find(String name, BigDecimal minFee, BigDecimal maxFee);
	
	List<Restaurant> findUsingCriteria(String name, BigDecimal minFee, BigDecimal maxFee);
	
	List<Restaurant> findFreeDeliveryFee(String name);

}