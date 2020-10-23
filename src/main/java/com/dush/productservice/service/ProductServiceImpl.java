/**
 * 
 */
package com.dush.productservice.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dush.productservice.dao.ProductDao;
import com.dush.productservice.exception.PriceEngineException;
import com.dush.productservice.exception.ProductServiceException;
import com.dush.productservice.model.CartVO;
import com.dush.productservice.model.Product;
import com.dush.productservice.model.ProductPrice;
import com.dush.productservice.model.ProductRequest;
import com.dush.productservice.model.ProductResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author ddush
 *
 */
@Service
public class ProductServiceImpl implements ProductService {

	/*** The logger ***/
	private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	/*** The error message ***/
	private static final String ERROR_MESSAGE="Exception thrown in the service";
	
	@Autowired
	PriceEngineService priceEngineService;
	
	@Autowired
	ProductDao productDao;
	
	/***
	 * {@inheritDoc}
	 */
	@Override
	public boolean addCart(ProductRequest productRequest, int userId) throws ProductServiceException, PriceEngineException {
		
		try {
			boolean flag =false;
			Product product = productDao.findProductById(productRequest.getProductId());
			product.setRequestData(productRequest);
			product = priceEngineService.generatePrices(product);
					
			flag = productDao.findProductInCart(userId, productRequest.getProductId());
			if(flag) {
				log.debug("Product found in table: {}",productRequest.getProductId());
				return productDao.saveCart(productRequest.getProductId(), productRequest.getCartons(), productRequest.getUnits());		
			}
			else {
				log.debug("Product not found in table: {}",productRequest.getProductId());
				return productDao.saveCart(userId, product.getId(), product.getCartons(), product.getUnits());	
			}
			
		}
		catch(ProductServiceException productServiceException) {
			throw productServiceException;
		}
		catch(PriceEngineException priceEngineException) {
			throw priceEngineException;
		}
		catch(Exception e) {
			throw new ProductServiceException(e,ERROR_MESSAGE);
		}
	}
	
	/***
	 * {@inheritDoc}
	 */
	@Override
	public List<CartVO> getCartData(int userId) throws ProductServiceException, PriceEngineException  {
		try {
			List<Product> productList = productDao.findProductByUserId(userId);
			List<CartVO> cartDataList = new ArrayList<>();
			log.debug("Product count in cart: {}",productList.size());
			
			for (Product product : productList) {
				product = priceEngineService.generatePrices(product);	
				cartDataList.add(new CartVO(product));
			}
			
			return cartDataList;
		}
		catch(ProductServiceException productServiceException) {
			throw productServiceException;
		}
		catch(PriceEngineException priceEngineException) {
			throw priceEngineException;
		}
		catch(Exception e) {
			throw new ProductServiceException(e,ERROR_MESSAGE);
		}
		
	}
	
	/***
	 * {@inheritDoc}
	 */
	@Override
	public List<ProductResponse> getProducts() throws ProductServiceException {
		
		try {
			List<Product> productList = productDao.findAllProduct();
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.convertValue(productList, new TypeReference<List<ProductResponse>>() {});
		}
		catch(ProductServiceException productServiceException) {
			throw productServiceException;
		}
		catch(Exception e) {
			throw new ProductServiceException(e,ERROR_MESSAGE);
		}
	}
	
	/***
	 * {@inheritDoc}
	 */
	@Override
	public List<ProductPrice> getPriceList(int productId) throws ProductServiceException, PriceEngineException  {
		try {
			Product product = productDao.findProductById(productId);
			return calculatePrice(product);
		}
		catch(ProductServiceException productServiceException) {
			throw productServiceException;
		}
		catch(PriceEngineException priceEngineException) {
			throw priceEngineException;
		}
		catch(Exception e) {
			throw new ProductServiceException(e,ERROR_MESSAGE);
		}
		
	}
	
	/***
	 * The method is used to generate price list.
	 * 
	 * @param product
	 * @return
	 * @throws PriceEngineException
	 */
	private List<ProductPrice> calculatePrice(Product product) throws PriceEngineException {
		List<ProductPrice> priceList = new ArrayList<>();
		for (int i = 1; i <= 50; i++) {
			product.setUnits(i);
			product.setCartons(0);
			product = priceEngineService.generatePrices(product);
			priceList.add(new ProductPrice(product, i));		
		}
		
		return priceList;
	}
	
	/***
	 * {@inheritDoc}
	 */
	@Override
	public boolean romveProductFromCart(int userId, int productId) throws ProductServiceException {
		try {
			return productDao.romveProductFromCart(userId, productId);
		} catch(ProductServiceException productServiceException) {
			throw productServiceException;
		}
		catch(Exception e) {
			throw new ProductServiceException(e,ERROR_MESSAGE);
		}
	}
	
}
