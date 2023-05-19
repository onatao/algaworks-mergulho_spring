package com.devnatao.logapi.execeptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice	
public class ExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 *  MessageSource is one Spring Framework interface to resolve messages
	 *  will insert the custom message write on messages.properties
	 */
	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		// creating field list -> List<Exception.Field> 
		// first: Exception (class name), second Field (static class created on Exception.class)
		List<Exception.Field> fields = new ArrayList<>();
		
		// populating field list 
		for (ObjectError error: ex.getBindingResult().getAllErrors()) {
			// Obtaining field(error) name
			String fieldName = ((FieldError) error).getField();
			// getting default error message - LocalContextHolder.getLocal() set process localization as locale
			String errorMessage = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			// adding on fields list
			fields.add(new Exception.Field(fieldName, errorMessage));
		}
		
		Exception exception = new Exception();
		exception.setStatus(status.value());
		exception.setMoment(LocalDateTime.now());
		exception.setTitle("Preencha os campos corretamente.");
		// adding fiels list (created on Exception.class)
		exception.setFields(fields);
		return handleExceptionInternal(ex, exception, headers, status, request);
	}
	
}
