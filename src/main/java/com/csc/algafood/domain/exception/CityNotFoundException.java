package com.csc.algafood.domain.exception;

public class CityNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;
	
	public CityNotFoundException(String message) {
		super(message);
	}
	
	public CityNotFoundException(Long stateId) {
		super(
				
				String.format("No city with the given id (%d) was found", stateId)
			
				);
	}
	
	

}
