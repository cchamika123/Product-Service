/**
 * 
 */
package com.dush.productservice.model;

/**
 * @author ddush
 *
 */
public class ResponseVO {
	
	public enum StatusType{
		OK, FAIL	
	}

	/*** status ***/
	private StatusType status;
	
	/*** data ***/
	private Object data;
	
	
	public ResponseVO() {
	}
	
	public ResponseVO(StatusType status, Object data) {
		super();
		this.status = status;
		this.data = data;
	}

	/**
	 * @return the status
	 */
	public StatusType getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(StatusType status) {
		this.status = status;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

	public ResponseVO success(Object data) {
		return new ResponseVO(StatusType.OK, data);
	}
	
	public ResponseVO fail(Object data) {
		return new ResponseVO(StatusType.FAIL, data);
	}
	
	
}
