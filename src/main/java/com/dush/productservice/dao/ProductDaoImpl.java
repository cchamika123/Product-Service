/**
 * 
 */
package com.dush.productservice.dao;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dush.productservice.exception.ProductServiceException;
import com.dush.productservice.model.Product;

/**
 * @author ddush
 *
 */
@Repository
@Transactional(value = "trnasactionManager")
public class ProductDaoImpl implements ProductDao {

	
	private static final Logger log = LoggerFactory.getLogger(ProductDaoImpl.class);
	
	private static final String ERROR_MESSAGE="Exception throw while accessing database";

	@Autowired
	private SessionFactory sessionFactory;

	/***
	 * {@inheritDoc}
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public boolean saveCart(int id, int cartons, int units) throws ProductServiceException {
		log.debug("Product updating in table:: productId: {}",id);
		try {
			Query query = sessionFactory.getCurrentSession()
					.createNativeQuery(UPDATE_INTO_CART)
					.setParameter("id", id)
					.setParameter("cartons", cartons)
					.setParameter("units", units);
			
			return (query.executeUpdate() > 0);
		} catch (Exception e) {
			throw new ProductServiceException(e,ERROR_MESSAGE);
		}
	}
	
	/***
	 * {@inheritDoc}
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public boolean saveCart(int userId, int productId, int cartons, int units) throws ProductServiceException {
		log.debug("Product inserting in table:: productId: {}, userId: {}",productId, userId);
		try {
			Query query = sessionFactory.getCurrentSession()
					.createNativeQuery(INSERT_INTO_CART)
					.setParameter("userId", userId)
					.setParameter("productId", productId)
					.setParameter("cartons", cartons)
					.setParameter("units", units);
			
			return (query.executeUpdate() > 0);
		} catch (Exception e) {
			throw new ProductServiceException(e,ERROR_MESSAGE);
		}
	}
	
	/***
	 * {@inheritDoc}
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
	@Override
	public boolean findProductInCart(int userId, int productId) throws ProductServiceException {
		log.debug("Getting product:: productId: {}, userId: {}",productId, userId);
		try {
			Query query = sessionFactory.getCurrentSession()
					.createNativeQuery(ChECK_CART)
					.setParameter("userId", userId)
					.setParameter("productId", productId);
			return ((BigInteger)query.getSingleResult()).intValue() > 0;
		}
		catch (Exception e) {
			throw new ProductServiceException(e,ERROR_MESSAGE);
		}
	}
	
	/***
	 * {@inheritDoc}
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Product> findProductByUserId(int userId) throws ProductServiceException {
		log.debug("Getting product list:: userId: {}", userId);
		try {
			Query query = sessionFactory.getCurrentSession()
					.createNativeQuery(GET_PRODUCT_BY_USERID)
					.setParameter("userId", userId);
			query = getQueryCoumns(query);
			return query.getResultList();
		}
		catch (Exception e) {
			throw new ProductServiceException(e, ERROR_MESSAGE);
		}
	}
	
	/***
	 * {@inheritDoc}
	 */
	@SuppressWarnings({ "rawtypes", "deprecation", "unchecked" })
	@Override
	public Product findProductById(int productId) throws ProductServiceException {
		log.debug("Getting product:: productId: {}", productId);
		try {
			Query query = sessionFactory.getCurrentSession()
					.createNativeQuery(GET_ALL_PRODUCT_BY_ID)
					.setParameter("productId", productId)
					.addScalar("id", StandardBasicTypes.INTEGER)
					.addScalar("name", StandardBasicTypes.STRING)
					.addScalar("discount", StandardBasicTypes.BIG_DECIMAL)
					.addScalar("perCarton", StandardBasicTypes.INTEGER)
					.addScalar("singleUnitRate", StandardBasicTypes.BIG_DECIMAL)
					.addScalar("cartonPrice", StandardBasicTypes.BIG_DECIMAL)
					.addScalar("unitsPerCarton", StandardBasicTypes.INTEGER)
					.setResultTransformer(Transformers.aliasToBean(Product.class));
			
			return getValidProduct( query.getResultList());
		}
		catch (ProductServiceException ex) {
			throw new ProductServiceException(ex,ERROR_MESSAGE);
		}catch (Exception e) {
			throw new ProductServiceException(e,ERROR_MESSAGE);
		}
	}
	

	/***
	 * {@inheritDoc}
	 */
	@SuppressWarnings({ "rawtypes", "deprecation", "unchecked" })
	@Override
	public List<Product> findAllProduct() throws ProductServiceException {
		log.debug("Getting all product");
		try {
			Query query = sessionFactory.getCurrentSession()
					.createNativeQuery(GET_ALL_PRODUCT)
					.addScalar("id", StandardBasicTypes.INTEGER)
					.addScalar("name", StandardBasicTypes.STRING)
					.addScalar("cartonPrice", StandardBasicTypes.BIG_DECIMAL)
					.addScalar("description", StandardBasicTypes.STRING)
					.setResultTransformer(Transformers.aliasToBean(Product.class));
			return query.getResultList();
			
		} catch (Exception e) {
			throw new ProductServiceException(e,ERROR_MESSAGE);
		}
	}
	
	private Product getValidProduct(List<Product> productList) throws ProductServiceException {
		if (!productList.isEmpty()) {
			return productList.get(0);
		}
		else {
			throw new ProductServiceException(new Exception(),"No Product found in database");
		}
	}
	
	@SuppressWarnings({ "rawtypes", "deprecation" })
	private Query getQueryCoumns(Query query) {
		return  ((NativeQuery) query).addScalar("id", StandardBasicTypes.INTEGER)
				.addScalar("name", StandardBasicTypes.STRING)
				.addScalar("cartons", StandardBasicTypes.INTEGER)
				.addScalar("units", StandardBasicTypes.INTEGER)
				.addScalar("discount", StandardBasicTypes.BIG_DECIMAL)
				.addScalar("perCarton", StandardBasicTypes.INTEGER)
				.addScalar("singleUnitRate", StandardBasicTypes.BIG_DECIMAL)
				.addScalar("cartonPrice", StandardBasicTypes.BIG_DECIMAL)
				.addScalar("unitsPerCarton", StandardBasicTypes.INTEGER)
				.setResultTransformer(Transformers.aliasToBean(Product.class));
		 
	}
	
	/***
	 * {@inheritDoc}
	 */
	@Override
	public boolean romveProductFromCart(int userId, int productId) throws ProductServiceException {
		log.debug("Removing product from table:: productId: {}, userId: {}", productId, userId);
		try {
			Query query = sessionFactory.getCurrentSession()
					.createNativeQuery(DELETE_FROM_CART)
					.setParameter("userId", userId)
					.setParameter("productId", productId);				
			
			return (query.executeUpdate() > 0);
			
		} catch (Exception e) {
			throw new ProductServiceException(e, ERROR_MESSAGE);
		}
	}
}
