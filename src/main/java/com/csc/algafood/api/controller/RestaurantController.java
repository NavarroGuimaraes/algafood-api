package com.csc.algafood.api.controller;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.csc.algafood.domain.exception.RestaurantNotFoundException;
import com.csc.algafood.domain.model.Restaurant;
import com.csc.algafood.domain.service.RestaurantService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
	
	@Autowired
	private RestaurantService service;
	
	@GetMapping
	public ResponseEntity<?> findAll(){
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id){
		try {
			return ResponseEntity.ok(service.findById(id));
		} catch (RestaurantNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/count/cookery/{id}")
	public ResponseEntity<?> countByCookeryId(@PathVariable("id") Long cookeryId) {
		return ResponseEntity.ok(service.countByCooeryId(cookeryId));
	}
	
	@GetMapping("/find")
	public ResponseEntity<?> find(@RequestParam(required = false) String name,
			@RequestParam(required = false) BigDecimal minFee,
			@RequestParam(required = false) BigDecimal maxFee) {
		
		return ResponseEntity.ok(service.find(name, minFee, maxFee));
		
	}
	
	@GetMapping("/free-delivery-fee")
	public ResponseEntity<?> findFreeDeliveryFeeRestaurants(String name) {
		return ResponseEntity.ok(service.findFreeDeliveryFeeRestaurants(name));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Restaurant save(@RequestBody Restaurant restaurant) {
			return service.save(restaurant);
	}
	
	@PutMapping("/{id}")
	public Restaurant update(@RequestBody Restaurant restaurant, @PathVariable("id") Long id) {
			return service.update(restaurant, id);		
	}
	
	@PatchMapping("/{id}")
	public Restaurant partialUpdate(@RequestBody Map<String, Object> fields, @PathVariable Long id) {
		
		Restaurant currentRestaurant = service.findById(id);
		
		if (currentRestaurant == null) {
			throw new RestaurantNotFoundException(id);
		}
		
		merge(fields, currentRestaurant);
		
		return update(currentRestaurant, id);
		
	}
	
	private void merge(Map<String, Object> fields, Restaurant currentRestaurant) {
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurant newRestaurant = objectMapper.convertValue(fields, Restaurant.class);
		
		fields.forEach((keyPropertyName, value) -> {
			//finds the field which has a value and exists in Restaurant class 
			Field field = ReflectionUtils.findField(Restaurant.class, keyPropertyName);
			field.setAccessible(true);
			
			//finds the value which the field has the same property in the newRestaurant and in the map 
			Object newValue = ReflectionUtils.getField(field, newRestaurant);
			
			
			ReflectionUtils.setField(field, currentRestaurant, newValue);
		});
	}

}
