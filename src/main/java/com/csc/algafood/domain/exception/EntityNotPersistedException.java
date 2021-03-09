package com.csc.algafood.domain.exception;

public class EntityNotPersistedException extends BusinessException{
	
	private static final long serialVersionUID = 1L;
	
	public EntityNotPersistedException(String message) {
		super(message);
	}

}
