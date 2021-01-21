package com.Forecast.Forecast.Model.Exceptions;

/** Rappresenta un'eccezzione personalizzata di tipo IllegalArgumentException.
 * 
*/

public class FilterIllegalArgumentException extends ApiRequestException{


	private static final long serialVersionUID = 1L;

	public FilterIllegalArgumentException() {
		super();
	}

	public FilterIllegalArgumentException(String message) {
		super(message);
	}
}

