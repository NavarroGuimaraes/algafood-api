package com.csc.algafood.domain.exception;

public class StateNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;
	
	public StateNotFoundException(String message) {
		super(message);
	}
	
	public StateNotFoundException(Long stateId) {
		super(
				
				String.format("No state with the given id (%d) was found", stateId)
			
				);
	}
	
	

}
