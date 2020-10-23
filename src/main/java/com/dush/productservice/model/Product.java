package com.dush.productservice.model;

import java.math.BigDecimal;


public class Product extends ProductResponse{
    
	/*** units per carton***/
	private int unitsPerCarton;
	
	/*** no of cartons***/
	private int cartons;
   
	/*** no of units ***/
	private int units;
    
	/*** discount ***/
	private BigDecimal discount;
   
	/*** per carton ***/
	private int perCarton;
    
	/*** single unit rate ***/
	private BigDecimal singleUnitRate;
	
	/*** total price ***/
	private BigDecimal totalPrice;
	
	/*** total unit price ***/
	private BigDecimal totalUnitPrice;
	
	/*** total carton price ***/
	private BigDecimal totalCartonPrice;
	
	
	/**
	 * @return the unitsPerCarton
	 */
	public int getUnitsPerCarton() {
		return unitsPerCarton;
	}

	/**
	 * @param unitsPerCarton the unitsPerCarton to set
	 */
	public void setUnitsPerCarton(int unitsPerCarton) {
		this.unitsPerCarton = unitsPerCarton;
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
	 * @return the discount
	 */
	public BigDecimal getDiscount() {
		return discount;
	}

	/**
	 * @param discount the discount to set
	 */
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	/**
	 * @return the perCarton
	 */
	public int getPerCarton() {
		return perCarton;
	}

	/**
	 * @param perCarton the perCarton to set
	 */
	public void setPerCarton(int perCarton) {
		this.perCarton = perCarton;
	}

	/**
	 * @return the singleUnitRate
	 */
	public BigDecimal getSingleUnitRate() {
		return singleUnitRate;
	}

	/**
	 * @param singleUnitRate the singleUnitRate to set
	 */
	public void setSingleUnitRate(BigDecimal singleUnitRate) {
		this.singleUnitRate = singleUnitRate;
	}

	/**
	 * @return the totalPrice
	 */
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	

	/**
	 * @return the totalUnitPrice
	 */
	public BigDecimal getTotalUnitPrice() {
		return totalUnitPrice;
	}

	/**
	 * @param totalUnitPrice the totalUnitPrice to set
	 */
	public void setTotalUnitPrice(BigDecimal totalUnitPrice) {
		this.totalUnitPrice = totalUnitPrice;
	}

	/**
	 * @return the totalCartonPrice
	 */
	public BigDecimal getTotalCartonPrice() {
		return totalCartonPrice;
	}

	/**
	 * @param totalCartonPrice the totalCartonPrice to set
	 */
	public void setTotalCartonPrice(BigDecimal totalCartonPrice) {
		this.totalCartonPrice = totalCartonPrice;
	}

	/**
	 * @param totalPrice the totalPrice to set
	 */
	public void setTotalPrice(BigDecimal totalPrice) {
		if(this.cartons >= this.perCarton) {
			this.totalPrice = totalPrice.subtract(totalPrice.multiply(this.discount));
		}else {
			this.totalPrice = totalPrice;
		}
	}

	/**
	 * 
	 * @param productRequest
	 */
	public void setRequestData(ProductRequest productRequest) {
		this.cartons = productRequest.getCartons();
		this.units = productRequest.getUnits();	
	}

}
