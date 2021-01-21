package com.Forecast.Forecast.Model.Exceptions;

public class ApiRequestException  extends RuntimeException{
	/** Rappresenta un'eccezione personalizzata di tipo RuntimeException.
	 */
    private static final long serialVersionUID = 1L;

	public ApiRequestException(String message)
	{

		super("not conformed, " + message);
	}
	public ApiRequestException(double error_threshold) {
		super("this error threshold cannot be negative");
	}
	
	public ApiRequestException(double error_threshold, double error_threshold2) {
		super("error threshold cannot be negative");
	}
	
	public ApiRequestException() {
	    super("error file not found");
	}

}

