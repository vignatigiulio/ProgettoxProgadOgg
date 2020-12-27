package com.Forecast.Forecast.Model.Exceptions;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

public class ApiException {
  private final String message;

  private final HttpStatus   httpstatus;
  private ZonedDateTime timestamp;
  public ApiException(String message, HttpStatus httpstatus, ZonedDateTime timestamp) {
	super();
	this.message = message;

	this.httpstatus = httpstatus;
	this.timestamp = timestamp;
}
public ZonedDateTime getTimestamp() {
	return timestamp;
}
public void setTimestamp(ZonedDateTime timestamp) {
	this.timestamp = timestamp;
}
public String getMessage() {
	return message;
}

public HttpStatus getHttpstatus() {
	return httpstatus;
}
  
  
}
