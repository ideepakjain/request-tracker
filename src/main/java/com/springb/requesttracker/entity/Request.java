package com.springb.requesttracker.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The Class Request.
 */
@Entity
public class Request {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/** The request category. */
	@Column(name = "category")
	private String requestCategory;

	/** The request details. */
	@Column(name = "details")
	private String requestDetails;

	/** The request priority. */
	@Column(name = "priority")
	private String requestPriority;

	/** The request subject. */
	@Column(name = "subject")
	private String requestSubject;

	/** The user. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request other = (Request) obj;
		return Objects.equals(id, other.id);
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Gets the request category.
	 *
	 * @return the requestCategory
	 */
	public String getRequestCategory() {
		return requestCategory;
	}

	/**
	 * Gets the request details.
	 *
	 * @return the requestDetails
	 */
	public String getRequestDetails() {
		return requestDetails;
	}

	/**
	 * Gets the request priority.
	 *
	 * @return the requestPriority
	 */
	public String getRequestPriority() {
		return requestPriority;
	}

	/**
	 * Gets the request subject.
	 *
	 * @return the requestSubject
	 */
	public String getRequestSubject() {
		return requestSubject;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	/**
	 * Sets the id.
	 *
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Sets the request category.
	 *
	 * @param requestCategory the requestCategory to set
	 */
	public void setRequestCategory(String requestCategory) {
		this.requestCategory = requestCategory;
	}

	/**
	 * Sets the request details.
	 *
	 * @param requestDetails the requestDetails to set
	 */
	public void setRequestDetails(String requestDetails) {
		this.requestDetails = requestDetails;
	}

	/**
	 * Sets the request priority.
	 *
	 * @param requestPriority the requestPriority to set
	 */
	public void setRequestPriority(String requestPriority) {
		this.requestPriority = requestPriority;
	}

	/**
	 * Sets the request subject.
	 *
	 * @param requestSubject the requestSubject to set
	 */
	public void setRequestSubject(String requestSubject) {
		this.requestSubject = requestSubject;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return String.format(
				"Request [id=%s, requestPriority=%s, requestCategory=%s, requestSubject=%s, requestDetails=%s, user=%s]",
				id, requestPriority, requestCategory, requestSubject, requestDetails, user);
	}

}
