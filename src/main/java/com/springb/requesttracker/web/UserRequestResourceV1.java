package com.springb.requesttracker.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springb.requesttracker.entity.Request;
import com.springb.requesttracker.entity.User;
import com.springb.requesttracker.exception.RequestNotFoundException;
import com.springb.requesttracker.exception.UserNotFoundException;
import com.springb.requesttracker.service.RequestService;
import com.springb.requesttracker.service.UserService;

/**
 * The Class UserResourceV1.
 */
@RestController
@RequestMapping("/api/v1")
public class UserRequestResourceV1 {

	/** The service. */
	@Autowired
	private UserService uService;

	/** The service. */
	@Autowired
	private RequestService rService;

	/**
	 * Save user.
	 *
	 * @param user the user
	 * @return the response entity
	 */
	@PostMapping("/users")
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		User saveUser = uService.signUp(user);
		return new ResponseEntity<User>(saveUser, HttpStatus.CREATED);
	}

	/**
	 * Gets the all users.
	 *
	 * @return the all users
	 */
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers() {
		List users = uService.findAllUsers();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	/**
	 * Gets the user.
	 *
	 * @param id the id
	 * @return the user
	 */
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserByUserId(@PathVariable Long id) {
		User user = uService.findUser(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	/**
	 * Update user.
	 *
	 * @param id   the id
	 * @param user the user
	 * @return the response entity
	 */
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUserDetailsByUserId(@PathVariable Long id, @RequestBody User user) {
		User userDB = uService.updateUserDetailsById(id, user);
		return new ResponseEntity<User>(userDB, HttpStatus.CREATED);
	}

	/**
	 * Delete by id.
	 *
	 * @param id the id
	 */
	@DeleteMapping("/users/{id}")
	public void deleteUserByUserId(@PathVariable Long id) {
		User request = uService.deleteUser(id);
		if (null == request) {
			throw new UserNotFoundException("id: " + id);
		}
	}

	/**
	 * Gets the all request.
	 *
	 * @param userId the user id
	 * @return the all request
	 */
	@GetMapping("/users/{id}/requests")
	public ResponseEntity<List<Request>> getAllRequest(@PathVariable("id") Long userId) {
		List<Request> list = rService.getAllRequest(userId);
		return new ResponseEntity<List<Request>>(list, HttpStatus.OK);
	}

	/**
	 * Find request by id.
	 *
	 * @param userId    the user id
	 * @param requestId the id
	 * @return the response entity
	 */
	@GetMapping("/users/{id}/requests/{rid}")
	public ResponseEntity<Request> findRequestOfUserByRequestId(@PathVariable("id") Long userId,
			@PathVariable("rid") Long requestId) {
		Request request = rService.findRequestById(userId, requestId);
		if (null == request) {
			throw new RequestNotFoundException("id: " + requestId);
		}
		return new ResponseEntity<Request>(request, HttpStatus.OK);
	}

	/**
	 * Save request.
	 *
	 * @param userId  the user id
	 * @param request the request
	 * @return the response entity
	 */
	@PostMapping("/users/{id}/requests")
	private ResponseEntity<Request> saveRequestOfUserId(@PathVariable("id") Long userId, @RequestBody Request request) {
		Request saveRequest = rService.saveRequest(userId, request);
		return new ResponseEntity<>(saveRequest, HttpStatus.CREATED);
	}

	/**
	 * Update request.
	 *
	 * @param userId    the user id
	 * @param requestId the id
	 * @param request   the request
	 * @return the response entity
	 */
	@PutMapping("/users/{id}/requests/{rid}")
	public ResponseEntity<Request> updateRequestDetailsByRequestId(@PathVariable("id") Long userId,
			@PathVariable("rid") Long requestId, @RequestBody Request request) {
		Request updateRequest = rService.updateRequest(userId, requestId, request);
		return new ResponseEntity<>(updateRequest, HttpStatus.CREATED);
	}

	/**
	 * Delete by id.
	 *
	 * @param userId    the user id
	 * @param requestId the request id
	 */
	@DeleteMapping("/users/{id}/requests/{rid}")
	public void deleteRequestOfUserByRequestId(@PathVariable("id") Long userId, @PathVariable("rid") Long requestId) {
		Request request = rService.deleteRequest(userId, requestId);
		if (null == request) {
			throw new RequestNotFoundException("requestId: " + requestId);
		}
	}

}
