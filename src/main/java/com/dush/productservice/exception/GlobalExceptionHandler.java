/**
 * 
 */
package com.dush.productservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.dush.productservice.model.ResponseVO;

/**
 * @author ddush
 *
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public ResponseVO handleRuntimeException(RuntimeException exception) {
		
		log.error("RuntimeException thrown from the system : {}",exception.getStackTrace());
		
		ResponseVO response = new ResponseVO();
		return response.fail("Runtime Exception");		
	}
	
	@ExceptionHandler(ProductServiceException.class)
	@ResponseBody
	public ResponseVO handleProductServiceException(ProductServiceException exception) {
		
		log.error("ProductServiceException thrown from the system : {}",exception.getStackTrace());
		
		ResponseVO response = new ResponseVO();
		return response.fail(exception.getMessage());		
	}
	
	@ExceptionHandler(PriceEngineException.class)
	@ResponseBody
	public ResponseVO handlePriceEngineException(ProductServiceException exception) {
		
		log.error("ProductServiceException thrown from the system : {}",exception.getStackTrace());
		
		ResponseVO response = new ResponseVO();
		return response.fail(exception.getMessage());		
	}
}
