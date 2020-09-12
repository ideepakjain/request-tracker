package com.springb.requesttracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The Class UserAlreadyExistsException.
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "User should register with new email")
public class UserAlreadyExistsException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1570349257824L;

	/**
	 * Instantiates a new user already exists exception.
	 *
	 * @param exception the exception
	 */
	public UserAlreadyExistsException(String exception) {
		super(exception);
	}
}
