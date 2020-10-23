/**
 * 
 */
package com.dush.productservice.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author ddush
 *
 */
public class ProductPrice {

	private int id;
	
	private String name;
	
	private int cartons;
	
	private int units;
	
	private BigDecimal price;
	
	public ProductPrice() {
		
	}

	public ProductPrice(Product product, int id) {
		this.id = id;
		this.name = product.getName();
		this.cartons = product.getCartons();
		this.units = product.getUnits();
		this.price = product.getTotalPrice().setScale(2, RoundingMode.HALF_UP);
		
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the cartons
	 */
	public int getCartons() {
		return cartons;
	}

	/**
	 * @param cartons the cartons to set
	 */
	public void setCartons(int cartons) {
		this.cartons = cartons;
	}

	/**
	 * @return the units
	 */
	public int getUnits() {
		return units;
	}

	/**
	 * @param units the units to set
	 */
	public void setUnits(int units) {
		this.units = units;
	}

	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price.setScale(2, RoundingMode.HALF_UP);
	}
	
	
	
}
