package com.springb.requesttracker.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The Class PersistentLogins.
 */
@Entity
@Table(name = "persistent_logins")
public class PersistentLogins {

	/** The username. */
	private String username;

	/** The series. */
	@Id
	private String series;

	/** The token. */
	private String token;

	/** The last used. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_used")
	private Date lastUsed = new Date();

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the series.
	 *
	 * @return the series
	 */
	public String getSeries() {
		return series;
	}

	/**
	 * Sets the series.
	 *
	 * @param series the new series
	 */
	public void setSeries(String series) {
		this.series = series;
	}

	/**
	 * Gets the token.
	 *
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Sets the token.
	 *
	 * @param token the new token
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * Gets the last used.
	 *
	 * @return the last used
	 */
	public Date getLastUsed() {
		return lastUsed;
	}

	/**
	 * Sets the last used.
	 *
	 * @param lastUsed the new last used
	 */
	public void setLastUsed(Date lastUsed) {
		this.lastUsed = lastUsed;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return String.format("PersistentLogins [username=%s, series=%s, token=%s, lastUsed=%s]", username, series,
				token, lastUsed);
	}

}
