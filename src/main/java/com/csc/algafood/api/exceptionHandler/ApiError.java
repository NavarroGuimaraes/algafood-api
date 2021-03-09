package com.csc.algafood.api.exceptionHandler;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApiError {

	@JsonProperty(value = "date_time")
	private LocalDateTime dateTime;
	private String message;
	
}
