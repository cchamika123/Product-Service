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
public class CartVO {

	private int id;
	
	private String name;
	
	private int cartons;
	
	private int units;
	
	private BigDecimal cartonPrice;
	
	private BigDecimal unitPrice;
	
	private BigDecimal totalPrice;

	public CartVO() {
	}
	/***
	 * 
	 * @param product
	 */
	public CartVO(Product product) {
		this.id=product.getId();
		this.name = product.getName();
		this.cartons = product.getCartons();
		this.units = product.getUnits();
		this.totalPrice = product.getTotalPrice().setScale(2, RoundingMode.HALF_UP);
		this.cartonPrice = product.getTotalCartonPrice().setScale(2, RoundingMode.HALF_UP);
		this.unitPrice = product.getTotalUnitPrice().setScale(2, RoundingMode.HALF_UP);
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
	 * @return the unitPrice
	 */
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	/**
	 * @param unitPrice the unitPrice to set
	 */
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice.setScale(2, RoundingMode.HALF_UP);
	}

	/**
	 * @return the totalPrice
	 */
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	/**
	 * @param totalPrice the totalPrice to set
	 */
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice.setScale(2, RoundingMode.HALF_UP);
	}

	@Override
	public String toString() {
		return "CartVO [name=" + name + ", cartons=" + cartons + ", units=" + units + ", cartonPrice="
				+ cartonPrice + ", unitPrice=" + unitPrice + ", totalPrice=" + totalPrice + "]";
	}
	
}
