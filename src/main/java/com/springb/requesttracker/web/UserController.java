package com.springb.requesttracker.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.springb.requesttracker.entity.User;
import com.springb.requesttracker.service.UserService;

/**
 * The Class UserController.
 */
@Controller
public class UserController {

	/** The user service. */
	@Autowired
	private UserService userService;

	/**
	 * Login.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView model = new ModelAndView();

		model.setViewName("user/login");
		return model;
	}

	/**
	 * Signup.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = { "/signup" }, method = RequestMethod.GET)
	public ModelAndView signup() {
		ModelAndView model = new ModelAndView();
		User user = new User();
		model.addObject("user", user);
		model.setViewName("user/signup");

		return model;
	}

	/**
	 * Creates the user.
	 *
	 * @param user          the user
	 * @param bindingResult the binding result
	 * @return the model and view
	 */
	@RequestMapping(value = { "/signup" }, method = RequestMethod.POST)
	public ModelAndView createUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView model = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());

		if (userExists != null) {
			bindingResult.rejectValue("email", "error.user", "This email already exists!");
		}
		if (bindingResult.hasErrors()) {
			model.setViewName("user/signup");
		} else {
			userService.signUp(user);
			model.addObject("msg", "User has been registered successfully!");
			model.addObject("user", new User());
			model.setViewName("user/signup");
		}

		return model;
	}

	/**
	 * Home.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = { "/home/home" }, method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		model.addObject("userId", user.getId());
		model.addObject("userName", user.getFirstname() + " " + user.getLastname());
		model.setViewName("home/home");
		return model;
	}

	/**
	 * Access denied.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = { "/access_denied" }, method = RequestMethod.GET)
	public ModelAndView accessDenied() {
		ModelAndView model = new ModelAndView();
		model.setViewName("errors/access_denied");
		return model;
	}
}