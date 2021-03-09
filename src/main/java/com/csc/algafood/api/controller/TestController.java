package com.csc.algafood.api.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.csc.algafood.domain.repository.CookeryRepository;
import com.csc.algafood.domain.repository.RestaurantRepository;

@RestController
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private CookeryRepository cRepository;
	
	@Autowired
	private RestaurantRepository rRepository;
	
	@GetMapping("/name")
	public ResponseEntity<?> findByName(@RequestParam("name") String name) {
		return ResponseEntity.ok(cRepository.findByNameContaining(name));
	}
	
	@GetMapping("/delivery-fee-between")
	public ResponseEntity<?> findByDeliveryFeeBetween(BigDecimal min, BigDecimal max) {
		return ResponseEntity.ok(rRepository.findByDeliveryFeeBetween(min, max));
	}
	
	@GetMapping("/top-two")
	public ResponseEntity<?> findTopTwo(String name) {
		return ResponseEntity.ok(rRepository.findTop2ByNameContaining(name));
	}

}
