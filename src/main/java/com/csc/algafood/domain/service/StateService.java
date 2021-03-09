package com.csc.algafood.domain.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.csc.algafood.domain.exception.EntityInUseException;
import com.csc.algafood.domain.exception.EntityNotPersistedException;
import com.csc.algafood.domain.exception.StateNotFoundException;
import com.csc.algafood.domain.model.State;
import com.csc.algafood.domain.repository.StateRepository;

@Service
public class StateService {
		
	@Autowired
	private StateRepository repository;
	
	public State update(State state, Long stateId) {
		
		State persistedState = findOrThrow(stateId);
		BeanUtils.copyProperties(state, persistedState, "id");
		state = repository.save(persistedState);
		return state;
		
	}
	
	public State save(State state) {
		return repository.save(state);
	}
	
	public void deleteById(Long stateId) {
		 
		try {
			
			repository.deleteById(stateId);
		
		} catch (EmptyResultDataAccessException e) {
			
			throw new EntityNotPersistedException(
					String.format("No state with the id %d was found in the database, thefore it can't be deleted", stateId));
			
		} catch (DataIntegrityViolationException e) {
			
			throw new EntityInUseException(
					String.format("The state with the id %d is in use so it cant be deleted", stateId));
			
		}
		
	}

	public State findOrThrow(Long id) {
		State state = repository.findById(id)
				.orElseThrow(() -> 
				new StateNotFoundException( id ));
		
		return state;
	}
	

}
