/**
 * 
 */
package com.dush.productservice.dao;

import java.util.List;

import com.dush.productservice.exception.ProductServiceException;
import com.dush.productservice.model.Product;

/**
 * @author ddush
 *
 */

public interface ProductDao {

	public static final String INSERT_INTO_CART="INSERT INTO CART(ID, P_ID, CARTONS, UNITS, USER_ID) "
			+ "VALUES (nextval('seq_cart'),:productId, :cartons, :units, :userId)";
			
	public static final String UPDATE_INTO_CART="UPDATE public.CART SET cartons=:cartons, units=:units WHERE "
			+ "P_ID=:id";
	
	public static final String GET_PRODUCT_BY_USERID="SELECT P.ID as id, P.NAME as name,T1.CARTONS as cartons, T1.UNITS as units, T1.DISCOUNT as discount, T1.PER_CARTON as perCarton, T1.SINGLE_UNIT_RATE as singleUnitRate, T1.CARTON_PRICE as cartonPrice, T1.UNITS_PER_CARTON as unitsPerCarton FROM "
			+ "(SELECT T.CARTONS, T.UNITS, T.P_ID, T.DISCOUNT, T.PER_CARTON, T.SINGLE_UNIT_RATE, PD.CARTON_PRICE, PD.UNITS_PER_CARTON FROM "
			+ "(SELECT C.CARTONS, C.UNITS, C.P_ID, PB.DISCOUNT, PB.PER_CARTON, PB.SINGLE_UNIT_RATE FROM CART C "
			+ "INNER JOIN PRODUCT_BONUS PB ON C.P_ID=PB.PRODUCT WHERE C.USER_ID=:userId) T "
			+ "INNER JOIN PRODUCT_DETAILS PD ON T.P_ID = PD.PRODUCT) T1 "
			+ "INNER JOIN PRODUCT P ON T1.P_ID=P.ID";
	
	public static final String GET_ALL_PRODUCT_BY_ID="SELECT P.ID as id, P.NAME as name, T.DISCOUNT as discount, T.PER_CARTON as perCarton, T.SINGLE_UNIT_RATE as singleUnitRate, T.CARTON_PRICE as cartonPrice, T.UNITS_PER_CARTON as unitsPerCarton "
			+ "FROM (SELECT PB.DISCOUNT, PB.PER_CARTON, PB.SINGLE_UNIT_RATE, PB.PRODUCT, PD.CARTON_PRICE, PD.UNITS_PER_CARTON FROM PRODUCT_BONUS PB "
			+ "INNER JOIN PRODUCT_DETAILS PD ON PB.PRODUCT = PD.PRODUCT WHERE PB.PRODUCT=:productId) T "
			+ "INNER JOIN PRODUCT P ON T.PRODUCT=P.ID";
	
	public static final String GET_ALL_PRODUCT="SELECT P.ID as id, P.NAME as name,P.DESCRIPTION as description, PD.CARTON_PRICE as cartonPrice FROM PRODUCT P "
			+ "INNER JOIN PRODUCT_DETAILS PD ON P.ID=PD.PRODUCT";

	public static final String DELETE_FROM_CART="DELETE from CART WHERE USER_ID=:userId AND P_ID=:productId";
	
	public static final String ChECK_CART="SELECT count(*) FROM CART WHERE USER_ID=:userId AND P_ID=:productId";
	
	/***
	 * This method is used to add new cart data into database.
	 * 
	 * @param userId
	 * @param productId
	 * @param cartons
	 * @param units
	 * @return Return true if successfully added. Else return false.
	 * @throws ProductServiceException
	 */
	boolean saveCart(int userId, int productId, int cartons, int units) throws ProductServiceException;

	/***
	 * This method is used to update cart data in database.
	 * 
	 * @param id
	 * @param cartons
	 * @param units
	 * @return Return true if successfully updated. Else return false.
	 * @throws ProductServiceException
	 */
	boolean saveCart(int id, int cartons, int units) throws ProductServiceException;

	/***
	 * This method is used to get product data for specific productId.
	 * 
	 * @param productId
	 * @return Return Product object
	 * @throws ProductServiceException
	 */
	Product findProductById(int productId) throws ProductServiceException;

	/***
	 * This method is used to get all the list of product data.
	 * 
	 * @return Return List<Product> object
	 * @throws ProductServiceException
	 */
	List<Product> findAllProduct() throws ProductServiceException;

	/***
	 * This method is used to get all the list of product data for specific userId.
	 * 
	 * @param userId
	 * @return Return List<Product> object
	 * @throws ProductServiceException
	 */
	List<Product> findProductByUserId(int userId) throws ProductServiceException;

	/***
	 * This method is used to remove data from cart.
	 * 
	 * @param userId
	 * @param productId
	 * @return Return true if successfully added. Else return false.
	 * @throws ProductServiceException
	 */
	boolean romveProductFromCart(int userId, int productId) throws ProductServiceException;

	boolean findProductInCart(int userId, int productId) throws ProductServiceException;

	
}
