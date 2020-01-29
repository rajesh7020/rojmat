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
import com.rajesh.model.Supplier;
import com.rajesh.model.User;
import com.rajesh.service.StateService;
import com.rajesh.service.SupplierService;

@Controller
@SessionAttributes("users")
public class SupplierController {
	@Autowired
	private SupplierService supplierService;
	@Autowired
	private StateService stateService;
	
	@PostMapping("/user/saveSupplier")
	public String saveSupplier(@ModelAttribute("command") Supplier supplier, BindingResult result, HttpSession session) throws RecordNotFoundException {
		User user = (User) session.getAttribute("users");
		if(user.getId() != null) {
			if(user.getId()>0) {
				supplier.setUsers(supplier.getUsers());
				supplierService.saveOrUpdateSupplier(supplier);
				return "redirect:/user/viewSupplier";
			}else {
				return "redirect:error";
			}
		}else {
			return "redirect:error";
		}	
	}
	@GetMapping("/user/viewSupplier")
	public ModelAndView viewSupplier(@ModelAttribute("command") Supplier supplier,BindingResult result, HttpSession session) throws RecordNotFoundException {
		Map<String,Object> model = new HashMap<String,Object>();
		User user = (User) session.getAttribute("users");
		if(user.getId() != null) {
			if(user.getId()>0) {
				model.put("suppliers", supplierService.getAllSuppliers(user.getId()));
				model.put("states", stateService.getAllStates(user.getId()));
				return new ModelAndView("supplier", model);
			}else {
				return new ModelAndView("redirect:error");
			}
		}else {
			return new ModelAndView("redirect:error");
		}	
	}
	@GetMapping("/user/deleteSupplierById")
	@ResponseBody
	public String deleteSupplier(Long supid, @ModelAttribute("command") Supplier supplier, BindingResult ressult) throws RecordNotFoundException {
		try {
			supplierService.deleteSupplierById(supplier.getSupid());
			return "success";
		}catch (RecordNotFoundException e) {
			return "failed";
		}
	}
	@GetMapping("/user/getSupplierById")
	@ResponseBody
	public Supplier updateSupplier(Long supid, @ModelAttribute("command") Supplier supplier, BindingResult result) throws RecordNotFoundException {
		return supplierService.getSupplierById(supplier.getSupid());
	}
	@GetMapping("/suppliers")
	@ResponseBody
	public List<Supplier> getSuppliers(Long userId) throws RecordNotFoundException {
		return supplierService.getAllSuppliers(userId);
	}
}
