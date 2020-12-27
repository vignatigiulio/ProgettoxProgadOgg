package com.Forecast.Forecast.Model.Exceptions;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ApiExceptionHandler {
	@ExceptionHandler(value = {ApiRequestException.class})
   public ResponseEntity<Object> handleApiRequestException(ApiRequestException e)
   {  HttpStatus badRequest = HttpStatus.NOT_ACCEPTABLE;
	   ApiException apiException= new ApiException(
			  e.getMessage(),
			  HttpStatus.BAD_REQUEST,
			  ZonedDateTime.now(ZoneId.of("Z"))
	);
	   return new ResponseEntity<>(apiException,badRequest);
   }
}
