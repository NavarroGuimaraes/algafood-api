package com.csc.algafood.domain.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.csc.algafood.domain.exception.BusinessException;
import com.csc.algafood.domain.exception.RestaurantNotFoundException;
import com.csc.algafood.domain.model.Cookery;
import com.csc.algafood.domain.model.Restaurant;
import com.csc.algafood.domain.repository.RestaurantRepository;

@Service
public class RestaurantService {
	
	@Autowired
	private RestaurantRepository repository;
	
	@Autowired @Lazy
	private CookeryService cookeryService;
	
	public List<Restaurant> findAll() {
		return repository.findAll();
	}
	
	public Restaurant findById(Long id) {
		return findOrThrow(id);
	}
	
	public Restaurant save(Restaurant restaurant) {
		
		Long cookeryId = restaurant.getCookery().getId();
		try {
			Cookery cookery = cookeryService.findOrThrow(cookeryId);
			
			restaurant.setCookery(cookery);
			
			return repository.save(restaurant);	
		} catch (RestaurantNotFoundException e) {
			throw new BusinessException(e.getMessage());
		}
		
	}
	
	public Restaurant update(Restaurant restaurant, Long restaurantId) {
		Restaurant persistedRestaurant = findOrThrow(restaurantId);
		
		Long cookeryId = restaurant.getCookery().getId();
		
		try {

			Cookery cookery = cookeryService.findOrThrow(cookeryId);
			BeanUtils.copyProperties(restaurant, persistedRestaurant, "id", "paymentMethods", "address", "createdAt");
			restaurant = repository.save(persistedRestaurant);
			restaurant.setCookery(cookery);
			return restaurant;
			
		} catch (RestaurantNotFoundException e) {
			throw new BusinessException(e.getMessage());
		}
		
	}
	
	public int countByCooeryId(Long id) {
		return repository.countByCookeryId(id);
	}
	
	public List<Restaurant> find(String name, BigDecimal minFee, BigDecimal maxFee) {
		return repository.findUsingCriteria(name, minFee, maxFee);
	}

	public List<Restaurant> findFreeDeliveryFeeRestaurants(String name) {

		return repository.findFreeDeliveryFee(name);
	}
	
	public Restaurant findOrThrow(Long restaurantId) {
		return repository.findById(restaurantId)
				.orElseThrow(() -> new RestaurantNotFoundException( restaurantId ));
	}

}
