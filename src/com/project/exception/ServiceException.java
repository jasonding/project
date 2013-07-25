/**
 * 
 */
package com.project.exception;

/**
 * service层异常
 * @author dingjs
 */
public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}
}
