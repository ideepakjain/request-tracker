package com.springb.requesttracker.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springb.requesttracker.entity.Request;
import com.springb.requesttracker.entity.Role;
import com.springb.requesttracker.entity.User;
import com.springb.requesttracker.exception.UserNotFoundException;
import com.springb.requesttracker.repository.RoleRespository;
import com.springb.requesttracker.repository.UserRepository;

/**
 * The Class UserServiceImpl.
 */
@Service
public class UserServiceImpl implements UserService {

	/** The repository. */
	@Autowired
	private UserRepository repository;

	/** The role respository. */
	@Autowired
	private RoleRespository roleRespository;

	/** The b crypt password encoder. */
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	/**
	 * Sign up.
	 *
	 * @param user the user
	 * @return the user
	 */
	@Override
	public User signUp(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(1);
		Role userRole = roleRespository.findByRole("ADMIN");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		return repository.save(user);
	}

	/**
	 * Find all users.
	 *
	 * @return the list
	 */
	@Override
	public List<User> findAllUsers() {
		List<User> result = new ArrayList<>();
		repository.findAll().forEach(result::add);
		return result;
	}

	/**
	 * Find user.
	 *
	 * @param id the id
	 * @return the user
	 */
	@Override
	public User findUser(Long id) {
		Optional<User> userDB = repository.findById(id);
		if (userDB.isPresent()) {
			return userDB.get();
		} else {
			throw new UserNotFoundException("No user found");
		}
	}

	/**
	 * Delete user.
	 *
	 * @param id the id
	 * @return the user
	 */
	@Override
	public User deleteUser(Long id) {
		User dbUser = findUser(id);
		repository.delete(dbUser);
		return dbUser;
	}

	/**
	 * Update user details by id.
	 *
	 * @param id   the id
	 * @param user the user
	 * @return the user
	 */
	@Override
	public User updateUserDetailsById(Long id, User user) {
		User updatedUser = findUser(id);
		updatedUser.setEmail(user.getEmail());
		updatedUser.setFirstname(user.getFirstname());
		updatedUser.setLastname(user.getLastname());
		updatedUser.setPassword(user.getPassword());
		return repository.save(updatedUser);
	}

	/**
	 * Find user by email.
	 *
	 * @param email the email
	 * @return the user
	 */
	@Override
	public User findUserByEmail(String email) {
		return repository.findByEmail(email);
	}

	/**
	 * Find user requests.
	 *
	 * @param userId the user id
	 * @return the list
	 */
	@Override
	public List<Request> findUserRequests(Long userId) {
		Optional<User> userDB = repository.findById(userId);
		if (userDB.isPresent()) {
			return userDB.get().getRequests();
		} else {
			throw new UserNotFoundException("No user found");
		}
	}

}
