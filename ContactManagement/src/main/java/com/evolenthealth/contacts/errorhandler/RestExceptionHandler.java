package com.evolenthealth.contacts.errorhandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{

	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public void notFoundErrorHandler(Exception ex, WebRequest request) {
		
	}
	@ExceptionHandler(ContactNotFoundException.class)
	public ResponseEntity<ErrorResonse> handleContactNotFoundException(Exception exception, WebRequest request){
		ErrorResonse error = new ErrorResonse(new Date(), HttpStatus.NOT_FOUND.toString(), request.getDescription(false));
		error.setMessage(exception.getMessage());
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<Object> handleEmptyResultSQLException(Exception exception, WebRequest request){
		Map<String, String> resMap = new LinkedHashMap<>();
		resMap.put("timestamp", new Date().toString());
		resMap.put("error", "Bad request");
		resMap.put("message", "error modifying invalid data");
		return new ResponseEntity<Object>(resMap,HttpStatus.BAD_REQUEST);
	}
	
		
}

