package com.springb.requesttracker.service;

import java.util.List;

import com.springb.requesttracker.entity.Request;

/**
 * The Interface RequestService.
 */
public interface RequestService {

	/**
	 * Save request.
	 *
	 * @param userId  the user id
	 * @param request the request
	 * @return the request
	 */
	Request saveRequest(Long userId, Request request);

	/**
	 * Gets the all request.
	 *
	 * @param userId the user id
	 * @return the all request
	 */
	List<Request> getAllRequest(Long userId);

	/**
	 * Update request.
	 *
	 * @param userId    the user id
	 * @param requestId the request id
	 * @param request   the request
	 * @return the request
	 */
	Request updateRequest(Long userId, Long requestId, Request request);

	/**
	 * Delete request.
	 *
	 * @param userId    the user id
	 * @param requestId the request id
	 * @return the request
	 */
	Request deleteRequest(Long userId, Long requestId);

	/**
	 * Find request by id.
	 *
	 * @param userId    the user id
	 * @param requestId the request id
	 * @return the request
	 */
	Request findRequestById(Long userId, Long requestId);

}
