package com.webencyclop.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.webencyclop.demo.model.User;
import com.webencyclop.demo.service.UserService;

@Controller
public class AuthenticationController {
	@Autowired
	private UserService userService;
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login"); // resources/template/login.html
		return modelAndView;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register(User user) {
		ModelAndView modelAndView = new ModelAndView();
		// User user = new User();
		// modelAndView.addObject("user", user); 
		modelAndView.setViewName("register"); // resources/template/register.html
		return modelAndView;
	}
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView registerUser(User user) {
		ModelAndView modelAndView = new ModelAndView();
		// User user = new User();
		// modelAndView.addObject("user", user); 
		userService.saveUser(user);
		modelAndView.setViewName("login"); // resources/template/register.html
		return modelAndView;
	}
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home"); // resources/template/home.html
		return modelAndView;
	}
}
