package com.rajesh.controller;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.logging.LogFactory;
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
	public String doLogin(ModelMap model, @ModelAttribute("command")User user, HttpSession session) {
		  	
		if(userService.loginUser(user.getEmail(), user.getPassword()) != null) {
	  		session.setAttribute("email",user.getEmail());
	  		session.setAttribute("user_id", user.getId());
	  		model.addAttribute("sucessLogin", "You are login sucessfully");
	  		logger.info("You are login sucessfully",user.getEmail());
	  		System.out.println("You are login sucessfully "+ user.getEmail());
	  		return "redirect:userdashboard";
	  	}else {
	  		System.out.println("Invalid Email/Password");
	  		logger.error("Invalid Email/Password");
			model.put("failed", "Invalid Email/Password");
			return "home";
	  	}  		
	}
	@GetMapping("/logout")
	public String doLogout(ModelMap model, @ModelAttribute("command")User user, HttpSession session) {
		session.removeAttribute("email");
		logger.info("you are logout successfully");
		return "home";
	}
}
