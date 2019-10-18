package com.rajesh.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rajesh.model.User;
import com.rajesh.service.UserService;

@Controller
public class UserController {
	private final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
    private UserService userService;
	@GetMapping("/")
	public String Home(Model model) {
		return "home";
	}
	@PostMapping("/user/register")
    public String registerNewUser(ModelMap model, @ModelAttribute("userRegister")@Valid User user, BindingResult bindingResult) {
        
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
            model.addAttribute("msgEmail", "your email is already registered");
            logger.info("your email Id is already registered");
            System.out.println("your email is already registered");
            return "home";
        } else {
            userService.saveUser(user);
            model.addAttribute("successMessage", "User has been registered successfully");
            logger.info("User has been registered successfully");
            System.out.println("User has been registered successfully");
            return "home";
        }
    }
	
	@PostMapping("/user/login") 
	public String doLogin(ModelMap model, @ModelAttribute("command")User user, HttpServletRequest request, HttpSession session) {
		  	
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		User users = userService.loginUser(email, password);
		if(users != null) {
			session.setAttribute("users", users);
			session.setMaxInactiveInterval(30000);
			System.out.println("User Id = "+users.getId());
			System.out.println("User Email = "+users.getEmail());
			System.out.println("User Shopname = "+users.getShopname());
	  		logger.info("You are login sucessfully",user.getEmail());
	  		return "redirect:userdashboard";
		}else {
			System.out.println("Invalid Email/Password");
	  		logger.error("Invalid Email/Password");
			model.put("failed", "Invalid Email/Password");
			return "home";
		}
	}
	@PostMapping("/user/checkstatus")
	@ResponseBody
	public Integer checkUserStatus(String email, ModelMap model, @ModelAttribute("command")User user, HttpSession session) {
			int userStatus = userService.isActiveUserStatus(email);
			System.out.println(userStatus);
			if(userStatus != 0) {
				System.out.println(userStatus);
				return userStatus;
			}else {
				System.out.println(userStatus);
				return userStatus;
			}
	}
	@GetMapping("/logout")
	public String doLogout(ModelMap model, @ModelAttribute("command")User user, HttpSession session) {
		session.removeAttribute("users");
		logger.info("you are logout successfully");
		return "home";
	}
}
