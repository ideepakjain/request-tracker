package com.springb.requesttracker.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springb.requesttracker.entity.Request;
import com.springb.requesttracker.exception.RequestNotFoundException;
import com.springb.requesttracker.service.RequestService;

/**
 * The Class RequestMvcController.
 */
@Controller
@RequestMapping("/users/{id}")
public class RequestMvcController {

	/** The service. */
	@Autowired
	RequestService service;

	/**
	 * Gets the all requests.
	 *
	 * @param userId the user id
	 * @param model  the model
	 * @return the all requests
	 */
	@RequestMapping
	public String getAllRequests(@PathVariable("id") Long userId, Model model) {
		List<Request> list = service.getAllRequest(userId);
		model.addAttribute("userId", userId);
		model.addAttribute("requests", list.isEmpty() ? null : list);
		return "list-requests";
	}

	/**
	 * Edits the request by id.
	 *
	 * @param userId the user id
	 * @param model  the model
	 * @param rid    the rid
	 * @return the string
	 * @throws RequestNotFoundException the request not found exception
	 */
	@RequestMapping(path = { "edit", "edit/{rid}" })
	public String editRequestById(@PathVariable("id") Long userId, Model model, @PathVariable("rid") Optional<Long> rid)
			throws RequestNotFoundException {
		model.addAttribute("userId", userId);
		if (rid.isPresent()) {
			Request entity = service.findRequestById(userId, rid.get());
			model.addAttribute("request", entity);
		} else {
			model.addAttribute("request", new Request());
		}
		return "add-edit-request";
	}

	/**
	 * Delete request by id.
	 *
	 * @param userId the user id
	 * @param model  the model
	 * @param rid    the rid
	 * @return the string
	 * @throws RequestNotFoundException the request not found exception
	 */
	@RequestMapping(path = "delete/{rid}")
	public String deleteRequestById(@PathVariable("id") Long userId, Model model, @PathVariable("rid") Long rid)
			throws RequestNotFoundException {
		model.addAttribute("userId", userId);
		service.deleteRequest(userId, rid);
		return "redirect:/users/" + userId;
	}

	/**
	 * Creates the or update request.
	 *
	 * @param userId  the user id
	 * @param model   the model
	 * @param request the request
	 * @return the string
	 */
	@RequestMapping(path = "createRequest", method = RequestMethod.POST)
	public String createOrUpdateRequest(@PathVariable("id") Long userId, Model model, Request request) {
		model.addAttribute("userId", userId);
		if (null != request.getId()) {
			service.updateRequest(userId, request.getId(), request);
		} else {
			service.saveRequest(userId, request);
		}
		model.addAttribute("requests", service.getAllRequest(userId));
		return "redirect:/users/" + userId;
	}
}