package com.springb.requesttracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springb.requesttracker.entity.Request;
import com.springb.requesttracker.entity.User;
import com.springb.requesttracker.exception.RequestNotFoundException;
import com.springb.requesttracker.repository.RequestRepository;

/**
 * The Class RequestServiceImpl.
 */
@Service
public class RequestServiceImpl implements RequestService {

	/** The request repository. */
	@Autowired
	private RequestRepository requestRepository;

	/** The user service. */
	@Autowired
	private UserService userService;

	/**
	 * Save request.
	 *
	 * @param userId  the user id
	 * @param request the request
	 * @return the request
	 */
	@Override
	public Request saveRequest(Long userId, Request request) {
		User userDb = userService.findUser(userId);
		request.setUser(userDb);
		return requestRepository.save(request);
	}

	/**
	 * Gets the all request.
	 *
	 * @param userId the user id
	 * @return the all request
	 */
	@Override
	public List<Request> getAllRequest(Long userId) {
		return userService.findUserRequests(userId);
	}

	/**
	 * Update request.
	 *
	 * @param userId    the user id
	 * @param requestId the request id
	 * @param request   the request
	 * @return the request
	 */
	@Override
	public Request updateRequest(Long userId, Long requestId, Request request) {
		userService.findUser(userId);
		Optional<Request> result = requestRepository.findById(requestId);
		if (result.isPresent()) {
			Request entity = result.get();
			entity.setRequestCategory(request.getRequestCategory());
			entity.setRequestDetails(request.getRequestCategory());
			entity.setRequestPriority(request.getRequestPriority());
			entity.setRequestSubject(request.getRequestSubject());
			requestRepository.save(entity);
			return entity;
		} else {
			throw new RequestNotFoundException("Request to update can not be null");
		}
	}

	/**
	 * Delete request.
	 *
	 * @param userId    the user id
	 * @param requestId the request id
	 * @return the request
	 */
	@Override
	public Request deleteRequest(Long userId, Long requestId) {
		userService.findUser(userId);
		Optional<Request> request = requestRepository.findById(requestId);
		if (request.isPresent()) {
			requestRepository.deleteById(requestId);
		} else {
			throw new RequestNotFoundException("Request must be present to delete");
		}
		return request.get();
	}

	/**
	 * Find request by id.
	 *
	 * @param userId    the user id
	 * @param requestId the request id
	 * @return the request
	 */
	@Override
	public Request findRequestById(Long userId, Long requestId) {
		User userDB = userService.findUser(userId);
		List<Request> requests = userDB.getRequests();
		if (null != requests && !requests.isEmpty()) {
			Optional<Request> request = requests.stream().filter(r -> r.getId().equals(requestId)).findFirst();
			if (!request.isPresent()) {
				throw new RequestNotFoundException("Request not be present");
			} else {
				return request.get();
			}
		}
		throw new RequestNotFoundException("Request not be present");
	}

}
