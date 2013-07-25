/**
 * 
 */
package com.project.exception;

/**
 * dao层异常
 * @author dingjs
 */
public class DaoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DaoException() {
		super();
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(Throwable cause) {
		super(cause);
	}
}
