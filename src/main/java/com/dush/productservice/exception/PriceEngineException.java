/**
 * 
 */
package com.dush.productservice.exception;

/**
 * @author ddush
 *
 */
public class PriceEngineException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private String message;

	public PriceEngineException() {
		super();
	}
	
	public PriceEngineException(Exception exception) {
		super(exception);
		this.message="PriceEngineException thrown from system";
	}
	
	public PriceEngineException(Exception exception, String message) {
		super(exception);
		this.message=message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
