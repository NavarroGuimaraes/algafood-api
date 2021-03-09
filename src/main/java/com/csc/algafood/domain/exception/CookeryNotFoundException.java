package com.csc.algafood.domain.exception;

public class CookeryNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;
	
	public CookeryNotFoundException(String message) {
		super(message);
	}
	
	public CookeryNotFoundException(Long stateId) {
		super(
				
				String.format("No cookery with the given id (%d) was found", stateId)
			
				);
	}
	
	

}
