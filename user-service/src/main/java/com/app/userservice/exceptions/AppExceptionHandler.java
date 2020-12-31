package com.app.userservice.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.app.userservice.model.ErrorResponseModel;
import com.fasterxml.jackson.core.JsonProcessingException;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { Exception.class ,UsernameNotFoundException.class, JsonProcessingException.class, InterruptedException.class})
	protected ResponseEntity<Object> handleanyException(Exception ex, WebRequest request) {
		String error = ex.getLocalizedMessage();
		if (error == null)
			error = ex.toString();
		ErrorResponseModel errorResponseModel = new ErrorResponseModel(new Date(), error);

		return new ResponseEntity<>(errorResponseModel, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	 @ExceptionHandler(value = TrainNotfoundException.class)
	   public ResponseEntity<Object> exception(TrainNotfoundException exception) {
	      return new ResponseEntity<>("Train not found", HttpStatus.NOT_FOUND);
	   }
}
