package com.rajesh.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.rajesh.model.Credit;
import com.rajesh.model.User;
import com.rajesh.service.CreditDebitService;
import com.rajesh.service.UserService;

@Controller
public class UserController {
	@Autowired
    private UserService userService;
	@Autowired
	private CreditDebitService creditDebitService;
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
            System.out.println("your email is already registered");
            return "home";
        } else {
            userService.saveUser(user);
            model.addAttribute("successMessage", "User has been registered successfully");
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
	  		System.out.println("You are login sucessfully "+ user.getEmail());
	  		return "redirect:userdashboard";
	  	}else {
	  		System.out.println("Invalid Email/Password");
			model.put("failed", "Invalid Email/Password");
			return "home";
	  	}  		
	}
}
