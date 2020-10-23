/**
 * 
 */
package com.dush.productservice.service;

import com.dush.productservice.exception.PriceEngineException;
import com.dush.productservice.model.Product;

/**
 * @author ddush
 *
 */
public interface PriceEngineService {


	Product generatePrices(Product product) throws PriceEngineException;

}
