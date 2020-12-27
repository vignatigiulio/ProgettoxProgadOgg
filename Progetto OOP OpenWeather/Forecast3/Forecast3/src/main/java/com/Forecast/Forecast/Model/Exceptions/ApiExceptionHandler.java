package com.Forecast.Forecast.Model.Exceptions;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/** Rappresenta la classe in sui vengono gestiti gli errori
 * generati da eventuali chiamate errate da parte del Client.
*/

@ControllerAdvice
public class ApiExceptionHandler {
	
	/**
	 * Risponde quando viene lanciato l'errore ApirequestException
	 * creando e restituendo un oggetto apiRequestException, con stato NOT_ACCEPTABLE.
	 * @return ResponseEntity di Object apiException
	 */
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
