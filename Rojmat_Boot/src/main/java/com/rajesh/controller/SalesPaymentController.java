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
import com.rajesh.model.SalesPayment;
import com.rajesh.model.User;
import com.rajesh.service.PaymentTypeService;
import com.rajesh.service.SalesPaymentService;

@Controller
@SessionAttributes("users")
public class SalesPaymentController {
	@Autowired
	private SalesPaymentService salesPaymentService;
	@Autowired
	private PaymentTypeService paymentTypeService;
	@PostMapping("/user/saveSalesPayment")
	public String saveSalesPayment(@ModelAttribute("command") SalesPayment salesPayment, BindingResult result, HttpSession session) throws RecordNotFoundException {
		User user = (User) session.getAttribute("users");
		if(user.getId() != null) {
			if(user.getId()>0) {
				salesPayment.setUsers(salesPayment.getUsers());
				salesPaymentService.saveOrUpdateSalesPayment(salesPayment);
				return "redirect:/user/viewSalesPayment";
			}else {
				return "redirect:error";
			}
		}else {
			return "redirect:error";
		}	
	}
	@GetMapping("/user/viewSalesPayment")
	public ModelAndView viewSalesPayment(@ModelAttribute("command") SalesPayment salesPayment,BindingResult result, HttpSession session) throws RecordNotFoundException {
		Map<String,Object> model = new HashMap<String,Object>();
		User user = (User) session.getAttribute("users");
		if(user.getId() != null) {
			if(user.getId()>0) {
				model.put("salesPayments", salesPaymentService.getAllSalesPayments(user.getId()));
				model.put("paymenttypes", paymentTypeService.getAllPaymentType(user.getId()));
				return new ModelAndView("salespayment", model);
			}else {
				return new ModelAndView("redirect:error");
			}
		}else {
			return new ModelAndView("redirect:error");
		}	
	}
	@GetMapping("/user/deleteSalesPaymentById")
	@ResponseBody
	public String deleteSalesPayments(Long sid, @ModelAttribute("command") SalesPayment salesPayment, BindingResult ressult, HttpSession session) throws RecordNotFoundException {
		try {
			salesPaymentService.deleteSalesPaymentById(salesPayment.getSid());
			return "success";
		}catch (RecordNotFoundException e) {
			return "failed";
		}
	}
	@GetMapping("/user/getSalesPaymentById")
	@ResponseBody
	public SalesPayment updateSalesPayment(Long sid, @ModelAttribute("command") SalesPayment salesPayment, BindingResult result, HttpSession session) throws RecordNotFoundException {
		return salesPaymentService.getSalesPaymentById(salesPayment.getSid());
	}
	@GetMapping("/salesPayments")
	@ResponseBody
	public List<SalesPayment> getSalesPayments(Long userId) throws RecordNotFoundException {
		return salesPaymentService.getAllSalesPayments(userId);
	}
}
