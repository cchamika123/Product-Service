/**
 * 
 */
package com.dush.productservice.exception;

/**
 * @author ddush
 *
 */

public class ProductServiceException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public ProductServiceException() {
		super();
	}
	
	public ProductServiceException(Exception exception) {
		super(exception);
		this.message="ProductServiceException thrown from system";
	}
	
	public ProductServiceException(Exception exception, String message) {
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


