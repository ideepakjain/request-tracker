package com.springb.requesttracker;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.springb.requesttracker.entity.Role;
import com.springb.requesttracker.entity.User;
import com.springb.requesttracker.repository.RoleRespository;
import com.springb.requesttracker.repository.UserRepository;

/**
 * The Class RequestTrackerApplication.
 */
@SpringBootApplication
@EnableJpaRepositories
@EnableCaching
public class RequestTrackerApplication implements CommandLineRunner {

	/** The role repository. */
	@Autowired
	private RoleRespository roleRepository;
	
	@Autowired
	private UserRepository userRepository;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(RequestTrackerApplication.class);

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(RequestTrackerApplication.class, args);
		LOGGER.debug("RequestTrackerApplication  >>>>>>>>>");
	}

	/**
	 * Run.
	 *
	 * @param args the args
	 * @throws Exception the exception
	 */
	@Override
	public void run(String... args) throws Exception {
		Role role = new Role();
		role.setId(1);
		role.setRole("ADMIN");
		roleRepository.save(role);
		
		User user = new User();
		user.setEmail("admin@springb.com");
		user.setFirstname("admin");
		user.setLastname("admin");
		user.setPassword("adminP");
		user.setActive(1);
		Set<Role> roles = new HashSet<Role>();
		Role roleD = new Role();
		roles.add(roleD );
		role.setId(1);
		user.setRoles(roles);
		userRepository.save(user );
		

	}

}
