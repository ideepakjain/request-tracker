package com.springb.requesttracker.service;

import java.util.List;

import com.springb.requesttracker.entity.Request;
import com.springb.requesttracker.entity.User;

/**
 * The Interface UserService.
 */
public interface UserService {

	/**
	 * Sign up.
	 *
	 * @param user the user
	 * @return the user
	 */
	User signUp(User user);

	/**
	 * Find all users.
	 *
	 * @return the list
	 */
	List findAllUsers();

	/**
	 * Find user.
	 *
	 * @param id the id
	 * @return the user
	 */
	User findUser(Long id);

	/**
	 * Delete user.
	 *
	 * @param id the id
	 * @return the user
	 */
	User deleteUser(Long id);

	/**
	 * Update user details by id.
	 *
	 * @param id   the id
	 * @param user the user
	 * @return the user
	 */
	User updateUserDetailsById(Long id, User user);

	/**
	 * Find user by email.
	 *
	 * @param email the email
	 * @return the user
	 */
	User findUserByEmail(String email);

	/**
	 * Find user requests.
	 *
	 * @param userId the user id
	 * @return the list
	 */
	List<Request> findUserRequests(Long userId);
}
