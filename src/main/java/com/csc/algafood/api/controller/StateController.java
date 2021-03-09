package com.csc.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.csc.algafood.domain.exception.EntityInUseException;
import com.csc.algafood.domain.exception.EntityNotPersistedException;
import com.csc.algafood.domain.exception.StateNotFoundException;
import com.csc.algafood.domain.model.State;
import com.csc.algafood.domain.repository.StateRepository;
import com.csc.algafood.domain.service.StateService;

@RestController
@RequestMapping("/states")
public class StateController {

	@Autowired
	private StateRepository repository;
	
	@Autowired
	private StateService service;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<State> findAll(){
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public State findById(@PathVariable Long id){
		return service.findOrThrow(id);	
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody State state, @PathVariable("id") Long id) {
		
		try {
			return ResponseEntity.ok(service.update(state, id));
		} catch (EntityNotPersistedException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody State state) {
		try {
			
			return ResponseEntity.status(HttpStatus.CREATED).body(service.save(state));
			
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long stateId) {
		try {
			service.deleteById(stateId);
			return ResponseEntity.noContent().build();
		} catch (StateNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (EntityInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
	
}
