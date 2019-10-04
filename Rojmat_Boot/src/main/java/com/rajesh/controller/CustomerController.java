package com.rajesh.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.rajesh.exception.RecordNotFoundException;
import com.rajesh.model.Customer;
import com.rajesh.service.CustomerService;
import com.rajesh.service.StateService;

@Controller
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private StateService stateService;
	@PostMapping("/user/saveCustomer")
	public String saveCustomer(@ModelAttribute("command") Customer customer, BindingResult result) throws RecordNotFoundException {
		customerService.saveOrUpdateCustomer(customer);
		return "redirect:/user/viewCustomer";
	}
	@GetMapping("/user/viewCustomer")
	public ModelAndView viewCustomer(@ModelAttribute("command") Customer customer,BindingResult result) {
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("customers", customerService.getAllCustomers());
		model.put("states", stateService.getAllStates());
		return new ModelAndView("customer", model);
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
	public List<Customer> getCustomers() {
		return customerService.getAllCustomers();
	}
}
