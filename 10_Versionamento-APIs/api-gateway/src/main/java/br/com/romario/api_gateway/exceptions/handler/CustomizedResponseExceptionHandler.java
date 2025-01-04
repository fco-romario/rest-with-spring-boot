package br.com.romario.api_gateway.exceptions.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.romario.api_gateway.exceptions.ExceptionsResponse;
import br.com.romario.api_gateway.exceptions.ResourceNotFoundException;

/*
	Use @RestControllerAdvice quando você deseja uma maneira simples e prática de tratar exceções em APIs REST, com respostas automaticamente serializadas em JSON.
	Use @ControllerAdvice + @RestController se você quiser mais controle sobre como tratar exceções globalmente e como definir os controladores, embora na prática seja menos comum, já que @RestControllerAdvice já simplifica esse fluxo.
 */

@ControllerAdvice
@RestController
public class CustomizedResponseExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionsResponse> handleAllExceptions(
			Exception ex, WebRequest request) {
		ExceptionsResponse exceptionsResponse = new ExceptionsResponse(
				new Date(),
				ex.getMessage(),
				request.getDescription(false));
		
		return new ResponseEntity<>(exceptionsResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<ExceptionsResponse> handleNotFoundExceptions (
			ResourceNotFoundException ex, WebRequest request) {
		ExceptionsResponse exceptionsResponse = new ExceptionsResponse(
				new Date(),
				ex.getMessage(),
				request.getDescription(false));
		
		return new ResponseEntity<>(exceptionsResponse, HttpStatus.NOT_FOUND);
	}
}
