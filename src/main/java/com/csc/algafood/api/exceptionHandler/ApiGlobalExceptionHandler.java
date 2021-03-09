package com.csc.algafood.api.exceptionHandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.csc.algafood.domain.exception.BusinessException;
import com.csc.algafood.domain.exception.EntityInUseException;
import com.csc.algafood.domain.exception.EntityNotFoundException;

@ControllerAdvice
public class ApiGlobalExceptionHandler {
	

	@ExceptionHandler({EntityNotFoundException.class})
	public ResponseEntity<?> handleNotFoundExceptions(EntityNotFoundException e){
		ApiError apiError = ApiError.builder()
				.dateTime(LocalDateTime.now())
				.message(e.getMessage()).build();
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(apiError);
	}
	
	@ExceptionHandler(EntityInUseException.class)
	public ResponseEntity<?> handleEntityInUseExceptions(BusinessException e) {
		ApiError apiError = ApiError.builder()
				.dateTime(LocalDateTime.now())
				.message(e.getMessage()).build();
		
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(apiError);
		
	}
	
	@ExceptionHandler({BusinessException.class})
	public ResponseEntity<?> handleBadRequestExceptions(BusinessException e) {
		ApiError apiError = ApiError.builder()
				.dateTime(LocalDateTime.now())
				.message(e.getMessage()).build();
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(apiError);
	}
	
	@ExceptionHandler({HttpMediaTypeNotSupportedException.class})
	public ResponseEntity<?> handleMediaNotSupportedExceptions() {
		ApiError apiError = ApiError.builder()
				.dateTime(LocalDateTime.now())
				.message("Media type not supported. Please consider sending json").build();
		
		return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
				.body(apiError);		
	}
	

}
