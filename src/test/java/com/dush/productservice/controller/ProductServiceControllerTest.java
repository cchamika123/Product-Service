/**
 * 
 */
package com.dush.productservice.controller;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.dush.productservice.exception.PriceEngineException;
import com.dush.productservice.exception.ProductServiceException;
import com.dush.productservice.model.CartVO;
import com.dush.productservice.model.ProductPrice;
import com.dush.productservice.model.ProductRequest;
import com.dush.productservice.model.ProductResponse;
import com.dush.productservice.service.ProductService;

/**
 * @author ddush
 *
 */
@RunWith(value = MockitoJUnitRunner.class)
@SpringBootTest
public class ProductServiceControllerTest {

	@Mock
	ProductService productService;
	
	@InjectMocks
	ProductServiceController productServiceController;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testAddCart() throws ProductServiceException, PriceEngineException {	
		ProductRequest productRequest = new ProductRequest();
		productRequest.setCartons(1);
		productRequest.setUnits(6);
		productRequest.setCartons(1);
		productRequest.setProductId(1);
	
		Mockito.when(productService.addCart(Mockito.any(ProductRequest.class), Mockito.anyInt()))
			.thenReturn(true);
		productServiceController.addCart(productRequest, 123);
	}
	
	@Test
	public void testGetCart() throws ProductServiceException, PriceEngineException {	
		ProductRequest productRequest = new ProductRequest();
		productRequest.setCartons(1);
		productRequest.setUnits(6);
		productRequest.setCartons(1);
		productRequest.setProductId(1);
	
		Mockito.when(productService.getCartData(Mockito.anyInt())).thenReturn(Arrays.asList(new CartVO()));
		productServiceController.getCart(123);
	}
	
	@Test
	public void testGetProducts() throws ProductServiceException  {	
		ProductRequest productRequest = new ProductRequest();
		productRequest.setCartons(1);
		productRequest.setUnits(6);
		productRequest.setCartons(1);
		productRequest.setProductId(1);
	
		Mockito.when(productService.getProducts()).thenReturn(Arrays.asList(new ProductResponse()));
		productServiceController.getProducts();
	}
	
	@Test
	public void testGetPriceList() throws ProductServiceException, PriceEngineException {	
		ProductRequest productRequest = new ProductRequest();
		productRequest.setCartons(1);
		productRequest.setUnits(6);
		productRequest.setCartons(1);
		productRequest.setProductId(1);
	
		Mockito.when(productService.getPriceList(Mockito.anyInt())).thenReturn(Arrays.asList(new ProductPrice()));
		productServiceController.getPriceList(123);
	}
	
}
