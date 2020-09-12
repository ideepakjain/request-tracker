package com.springb.requesttracker.exception;

import java.util.Date;

/**
 * The Class ExceptionResponse.
 */
public class ExceptionResponse {

	/** The timestamp. */
	private Date timestamp;

	/** The message. */
	private String message;

	/** The details. */
	private String details;

	/**
	 * Gets the timestamp.
	 *
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * Sets the timestamp.
	 *
	 * @param timestamp the new timestamp
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the details.
	 *
	 * @return the details
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * Sets the details.
	 *
	 * @param details the new details
	 */
	public void setDetails(String details) {
		this.details = details;
	}

	/**
	 * Instantiates a new exception response.
	 *
	 * @param timestamp the timestamp
	 * @param message   the message
	 * @param details   the details
	 */
	public ExceptionResponse(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	/**
	 * Instantiates a new exception response.
	 */
	public ExceptionResponse() {
		// TODO Auto-generated constructor stub
	}

}
