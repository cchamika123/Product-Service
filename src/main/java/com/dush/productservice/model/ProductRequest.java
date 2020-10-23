/**
 * 
 */
package com.dush.productservice.model;

/**
 * @author ddush
 *
 */
public class ProductRequest {

	private int productId;
	
	private int cartons;
	
	private int units;

	/**
	 * @return the productId
	 */
	public int getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(int productId) {
		this.productId = productId;
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
	
	
}
