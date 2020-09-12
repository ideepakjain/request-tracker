package com.springb.requesttracker.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springb.requesttracker.entity.User;

/**
 * The Interface UserRepository.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	/**
	 * Find by email.
	 *
	 * @param email the email
	 * @return the user
	 */
	User findByEmail(String email);

}
