package com.springb.requesttracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springb.requesttracker.entity.Role;

/**
 * The Interface RoleRespository.
 */
@Repository("roleRepository")
public interface RoleRespository extends JpaRepository<Role, Integer> {

	/**
	 * Find by role.
	 *
	 * @param role the role
	 * @return the role
	 */
	Role findByRole(String role);
}
