package com.csc.algafood.api.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.csc.algafood.api.exceptionHandler.ApiError;
import com.csc.algafood.domain.exception.BusinessException;
import com.csc.algafood.domain.exception.CityNotFoundException;
import com.csc.algafood.domain.exception.EntityInUseException;
import com.csc.algafood.domain.exception.EntityNotFoundException;
import com.csc.algafood.domain.model.City;
import com.csc.algafood.domain.service.CityService;

@RestController
@RequestMapping("/cities")
public class CityController {

	@Autowired
	private CityService service;
	
	@GetMapping
	public ResponseEntity<?> findAll(){
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public City findById(@PathVariable Long id) {
		return service.findOrThrow(id);
	}
	
	@PutMapping("/{id}")
	public City update(@PathVariable Long id, @RequestBody City city) {
			return service.update(city, id);
	}
	
	@PostMapping
	public City save(@RequestBody City city) {
			return service.save(city);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		
		try {
			
			service.deleteById(id);
			return ResponseEntity.noContent().build();
			
		} catch (CityNotFoundException e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			
		}
		
	}
	
}
