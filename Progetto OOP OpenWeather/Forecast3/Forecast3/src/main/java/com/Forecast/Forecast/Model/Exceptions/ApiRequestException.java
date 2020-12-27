package com.Forecast.Forecast.Model.Exceptions;

public class ApiRequestException  extends RuntimeException{
	/**
     * 
     */
    private static final long serialVersionUID = 1L;

	public ApiRequestException(String message)
	{
		super("non conformed " + message);
	}

	public ApiRequestException(float error_threshold) {
		super("this error threshold cannot be negative");
	}
	
	public ApiRequestException(float error_threshold, float error_threshold2) {
		super("error threshold cannot be negative");
	}

}
