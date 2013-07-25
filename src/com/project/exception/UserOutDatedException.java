/**
 * 
 */
package com.project.exception;

/**
 * 用户过时异常
 * @author dingjs
 */
public class UserOutDatedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UserOutDatedException() {
		super();
	}

	public UserOutDatedException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserOutDatedException(String message) {
		super(message);
	}

	public UserOutDatedException(Throwable cause) {
		super(cause);
	}
	

}
