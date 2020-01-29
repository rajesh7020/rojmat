package com.rajesh.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.rajesh.exception.RecordNotFoundException;
import com.rajesh.model.Customer;
import com.rajesh.model.User;
import com.rajesh.service.CustomerService;
import com.rajesh.service.StateService;

@Controller
@SessionAttributes("users")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private StateService stateService;
	
	@PostMapping("/user/saveCustomer")
	public String saveCustomer(@ModelAttribute("command") Customer customer, BindingResult result, HttpSession session) throws RecordNotFoundException {
		User user = (User) session.getAttribute("users");
		if(user.getId() != null) {
			if(user.getId()>0) {
				customer.setUsers(customer.getUsers());
				customerService.saveOrUpdateCustomer(customer);
				return "redirect:/user/viewCustomer";
			}else {
				return "redirect:error";
			}
		}else {
			return "redirect:error";
		}	
	}
	@GetMapping("/user/viewCustomer")
	public ModelAndView viewCustomer(@ModelAttribute("command") Customer customer,BindingResult result, HttpSession session) throws RecordNotFoundException {
		Map<String,Object> model = new HashMap<String,Object>();
		User user = (User) session.getAttribute("users");
		if(user.getId() != null) {
			if(user.getId()>0) {
				model.put("customers", customerService.getAllCustomers(user.getId()));
				model.put("states", stateService.getAllStates(user.getId()));
				return new ModelAndView("customer", model);
			}else {
				return new ModelAndView("redirect:error");
			}
		}else {
			return new ModelAndView("redirect:error");
		}	
	}
	@GetMapping("/user/deleteCustomerById")
	@ResponseBody
	public String deleteCustomer(Long custid, @ModelAttribute("command") Customer customer, BindingResult ressult) throws RecordNotFoundException {
		try {
			customerService.deleteCustomerById(customer.getCustid());
			return "success";
		}catch (RecordNotFoundException e) {
			return "failed";
		}
	}
	@GetMapping("/user/getCustomerById")
	@ResponseBody
	public Customer getCustomer(Long custid, @ModelAttribute("command") Customer customer, BindingResult result) throws RecordNotFoundException {
		return customerService.getCustomerById(customer.getCustid());
	}
	@GetMapping("/customers")
	@ResponseBody
	public List<Customer> getCustomers(Long userId) throws RecordNotFoundException {
		return customerService.getAllCustomers(userId);
	}
}
