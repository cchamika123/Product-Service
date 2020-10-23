/**
 * 
 */
package com.dush.productservice.service;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dush.productservice.exception.PriceEngineException;
import com.dush.productservice.model.Product;

/**
 * @author ddush
 *
 */
@Service
public class PriceEngineServiceImpl implements PriceEngineService {

	private static final Logger log = LoggerFactory.getLogger(PriceEngineServiceImpl.class);


	
	@Override
	public Product generatePrices(Product product) throws PriceEngineException {
		try {
			if (product != null) {
				log.debug("Generating prices");
        		int newCarton = calulateCartonCount(product.getUnits(), product.getUnitsPerCarton());
        		int newUnits = calulateUnitCount(product.getUnits(), product.getUnitsPerCarton());
        		
        		product.setCartons(product.getCartons() + newCarton);
        		product.setUnits(newUnits);
        		
        		BigDecimal perUnitPrice = product.getCartonPrice().add(product.getCartonPrice()
        				.multiply(product.getSingleUnitRate()));
        		perUnitPrice=perUnitPrice.divide(BigDecimal.valueOf(product.getUnitsPerCarton()));
        				
        		BigDecimal totalUnitPrice = perUnitPrice
        				.multiply(BigDecimal.valueOf(product.getUnits()));

        		BigDecimal totalCartonPrice = product.getCartonPrice()
        				.multiply(BigDecimal.valueOf(product.getCartons()));
        		
        		product.setTotalCartonPrice(totalCartonPrice);
        		product.setTotalUnitPrice(totalUnitPrice);
        		product.setTotalPrice(totalUnitPrice.add(totalCartonPrice));
	        }
		
		return product;
		}
		catch (Exception e) {
			throw new PriceEngineException(e, "Exceptin throw while generating price");
		}
		
	}
	
	private int calulateUnitCount(int units, int unitsPerCarton) {
		if(units>=unitsPerCarton) {
			return (units%unitsPerCarton);
		}
		return units;
	}
	
	private int calulateCartonCount(int units, int unitsPerCarton) {
		return units/unitsPerCarton;
	}
}
