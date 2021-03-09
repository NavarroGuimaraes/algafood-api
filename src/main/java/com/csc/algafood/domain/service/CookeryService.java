package com.csc.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.csc.algafood.domain.exception.CookeryNotFoundException;
import com.csc.algafood.domain.exception.EntityInUseException;
import com.csc.algafood.domain.model.Cookery;
import com.csc.algafood.domain.repository.CookeryRepository;

@Service
public class CookeryService {

	@Autowired
	private CookeryRepository repository;
	
	public Cookery save(Cookery cookery) {
		return repository.save(cookery);
	}
	
	public void delete(Long id) {
		try {
			
			repository.deleteById(id);
			
		} catch (EmptyResultDataAccessException e){
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("a cookery identified by %d was not found", id));
			throw new CookeryNotFoundException( id );
			
		} catch (DataIntegrityViolationException e) {
			
			throw new EntityInUseException(String.format("Cookery identified by %d"
					+ " is already in use and cannot be deleted", id));
			
		}
	}

	public boolean existsByName(String name) {
		return repository.existsByNameContainingIgnoreCase(name);
	}
	
	public Cookery findOrThrow(Long cookeryId) {
		return repository.findById(cookeryId)
				.orElseThrow( () -> new CookeryNotFoundException( cookeryId  ) );
	}
	
}
