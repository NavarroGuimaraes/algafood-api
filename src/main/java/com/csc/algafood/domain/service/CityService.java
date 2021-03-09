package com.csc.algafood.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.csc.algafood.domain.exception.BusinessException;
import com.csc.algafood.domain.exception.CityNotFoundException;
import com.csc.algafood.domain.exception.EntityInUseException;
import com.csc.algafood.domain.exception.StateNotFoundException;
import com.csc.algafood.domain.model.City;
import com.csc.algafood.domain.model.State;
import com.csc.algafood.domain.repository.CityRepository;

@Service
public class CityService {
	
	@Autowired
	private CityRepository repository;
	
	@Autowired
	private StateService stateService;
	
	public List<City> findAll() {
		return repository.findAll();
	}
	
	public City findById(Long id) {
		City city = repository.findById(id).orElseThrow(
				() -> new CityNotFoundException(id));
		
		return city;
	}
	
	public City save(City city) {
		Long cityId = city.getState().getId();
		try {
			State state = stateService.findOrThrow(cityId);
			
			city.setState(state);
			return repository.save(city);	
		} catch (StateNotFoundException e) {
			throw new BusinessException(e.getMessage());
		}
		
	}
	
	public City update(City city, Long id) {
		City foundCity = findOrThrow(id);
		
		Long stateId = city.getState().getId();
		try {
		
			State state = stateService.findOrThrow(stateId);
			city.setState(state);
			BeanUtils.copyProperties(city, foundCity, "id");
			city = repository.save(foundCity);
		
		} catch (StateNotFoundException e) {
			
			throw new BusinessException(e.getMessage());
			
		}
		
		return city;
	}
	
	public void deleteById(Long id) {
		
		try {
			repository.deleteById(id);	
		} catch (EmptyResultDataAccessException e) {
			throw new CityNotFoundException(
					String.format("Its not possible to delete a city that doesn't exist. the given id: %d", id));
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format("This city is in use and therefore cannot be deleted. id: (%d)", id));
		}
		
		
	}
	
	public City findOrThrow(Long cityId) {
		return repository.findById(cityId)
				.orElseThrow( () -> new CityNotFoundException(  cityId  ) );
	}

}
