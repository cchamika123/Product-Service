/**
 * 
 */
package com.dush.productservice.service;

import java.util.List;

import com.dush.productservice.exception.PriceEngineException;
import com.dush.productservice.exception.ProductServiceException;
import com.dush.productservice.model.CartVO;
import com.dush.productservice.model.ProductPrice;
import com.dush.productservice.model.ProductRequest;
import com.dush.productservice.model.ProductResponse;

/**
 * @author ddush
 *
 */
public interface ProductService {

	boolean addCart(ProductRequest productRequest, int userId) throws ProductServiceException, PriceEngineException;

	List<ProductResponse> getProducts() throws ProductServiceException;

	List<CartVO> getCartData(int userId) throws ProductServiceException, PriceEngineException;

	List<ProductPrice> getPriceList(int productId) throws ProductServiceException, PriceEngineException;

	boolean romveProductFromCart(int userId, int productId) throws ProductServiceException;

}
