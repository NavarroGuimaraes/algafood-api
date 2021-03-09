package com.csc.algafood.domain.exception;

public class RestaurantNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;
	
	public RestaurantNotFoundException(String message) {
		super(message);
	}
	
	public RestaurantNotFoundException(Long stateId) {
		super(
				
				String.format("No restaurant with the given id (%d) was found", stateId)
			
				);
	}
	
	

}
