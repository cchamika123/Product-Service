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
public class ProductResponse {
	
	/*** product id ***/
	private int id;
	
	/*** product name ***/
	private  String name;
    
	/*** carton price ***/
	private BigDecimal cartonPrice;
	
	/*** description ***/
	private String description;

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
	 * @return the cartonPrice
	 */
	public BigDecimal getCartonPrice() {
		return cartonPrice;
	}

	/**
	 * @param cartonPrice the cartonPrice to set
	 */
	public void setCartonPrice(BigDecimal cartonPrice) {
		this.cartonPrice = cartonPrice.setScale(2, RoundingMode.HALF_UP);
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
    
	

}
