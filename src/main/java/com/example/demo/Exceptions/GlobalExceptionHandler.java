package com.example.demo.Exceptions;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BadException.class)
	public ResponseEntity<?> handleBadRequest(BadException ex)
	{
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(createErrorResponse(ex.getMessage(),HttpStatus.BAD_REQUEST));
		
	}
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleNotFound(ResourceNotFoundException ex)
	{
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(createErrorResponse(ex.getMessage(),HttpStatus.NOT_FOUND));
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGlobal(Exception ex, HttpServletRequest request) {
	    String path = request.getRequestURI();

	    // Skip Swagger UI and OpenAPI docs
	    if (path.contains("/v3/api-docs") || path.contains("/swagger") || path.contains("/swagger-ui")) {
	        // Let Spring handle the error for Swagger requests
	        throw new RuntimeException(ex);
	    }

	    return ResponseEntity
	            .status(HttpStatus.INTERNAL_SERVER_ERROR)
	            .body(createErrorResponse("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR));
	}
	
	
	private Map<String,Object> createErrorResponse(String message,HttpStatus status){
	return Map.of(
			"timestamp" ,LocalDateTime.now(),
			"status",status.getReasonPhrase(),
			"message",message
			);
	}
}
