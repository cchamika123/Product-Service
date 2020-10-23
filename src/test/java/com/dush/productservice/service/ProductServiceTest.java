package com.dush.productservice.service;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.dush.productservice.dao.ProductDao;
import com.dush.productservice.exception.PriceEngineException;
import com.dush.productservice.exception.ProductServiceException;
import com.dush.productservice.model.Product;
import com.dush.productservice.model.ProductRequest;

@RunWith(value = MockitoJUnitRunner.class)
@SpringBootTest
public class ProductServiceTest {

	@Mock
	PriceEngineService priceEngineService;
	
	@Mock
	ProductDao productDao;

	@InjectMocks
	ProductServiceImpl productServiceImpl;

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

		Product product = new Product();
		product.setId(1);
		product.setCartonPrice(BigDecimal.valueOf(175.00));
		product.setDiscount(BigDecimal.valueOf(0.1));
		product.setPerCarton(3);;
		product.setUnitsPerCarton(20);
		
		Mockito.when(productDao.findProductById(Mockito.anyInt())).thenReturn(product);
		Mockito.when(productDao.findProductInCart(Mockito.anyInt(), (Mockito.anyInt()))).thenReturn(true);
		
		Mockito.when(productDao.saveCart(Mockito.anyInt(),Mockito.anyInt(),Mockito.anyInt()))
		.thenReturn(true);
		
		Mockito.when(priceEngineService.generatePrices(Mockito.any(Product.class)))
		.thenReturn(product);
		
		productServiceImpl.addCart(productRequest, 123);
	}
}
