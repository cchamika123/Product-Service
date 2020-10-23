/**
 * 
 */
package com.dush.productservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dush.productservice.exception.PriceEngineException;
import com.dush.productservice.exception.ProductServiceException;
import com.dush.productservice.model.ProductRequest;
import com.dush.productservice.model.ResponseVO;
import com.dush.productservice.service.ProductService;

/**
 * @author ddush
 *
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/product")
public class ProductServiceController {

	@Autowired
	ProductService productService;
	
	/***
	 * This method is used to add data to cart
	 * 
	 * @param productRequest
	 * @param userId
	 * @return Return true if successfully added. Else return false.
	 * @throws ProductServiceException
	 * @throws PriceEngineException
	 */
	@PostMapping(value = "/add/cart/{userId}", produces = "application/json")
	public ResponseVO addCart(@RequestBody ProductRequest productRequest, @PathVariable("userId") int userId) throws ProductServiceException, PriceEngineException {	
		return new ResponseVO().success(productService.addCart(productRequest,userId));
	}
	
	/***
	 * This method is used to get data from cart
	 * 
	 * @param userId
	 * @return Return Cart data
	 * @throws ProductServiceException
	 * @throws PriceEngineException
	 */
	@GetMapping(value = "/list/cart/{userId}", produces = "application/json")
	public ResponseVO getCart(@PathVariable("userId") int userId) throws ProductServiceException, PriceEngineException{	
		return new ResponseVO().success(productService.getCartData(userId));
	}
	
	/***
	 * This method is used to get all product data
	 * 
	 * @return Return List of product data
	 * @throws ProductServiceException
	 */
	@GetMapping(value = "/all", produces = "application/json")
	public ResponseVO getProducts() throws ProductServiceException {	
		return new ResponseVO().success(productService.getProducts());
	}
	
	/***
	 * This method is used to get proce list
	 * 
	 * @param productId
	 * @return return price list
	 * @throws ProductServiceException
	 * @throws PriceEngineException
	 */
	@GetMapping(value = "/{productId}/prices", produces = "application/json")
	public ResponseVO getPriceList( @PathVariable("productId") int productId) throws ProductServiceException, PriceEngineException{	
		return new ResponseVO().success(productService.getPriceList(productId));
	}
	
	@GetMapping(value = "/{productId}/remove/{userId}", produces = "application/json")
	public ResponseVO getPriceList(@PathVariable("userId") int userId, @PathVariable("productId") int productId) throws ProductServiceException, PriceEngineException{	
		return new ResponseVO().success(productService.romveProductFromCart(userId, productId));
	}
}
