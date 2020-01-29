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
import com.rajesh.model.PurchasePayment;
import com.rajesh.model.User;
import com.rajesh.service.PaymentTypeService;
import com.rajesh.service.PurchasePaymentService;

@Controller
@SessionAttributes("users")
public class PurchasePaymentController {
	@Autowired
	private PurchasePaymentService purchasePaymentService;
	@Autowired
	private PaymentTypeService paymentTypeService;
	@PostMapping("/user/savePurchasePayment")
	public String savePurchasePayment(@ModelAttribute("command") PurchasePayment purchasePayment, BindingResult result, HttpSession session) throws RecordNotFoundException {
		User user = (User) session.getAttribute("users");
		if(user.getId() != null) {
			if(user.getId()>0) {
				purchasePayment.setUsers(purchasePayment.getUsers());
				purchasePaymentService.saveOrUpdatePurchasePayment(purchasePayment);
				return "redirect:/user/viewPurchasePayment";
			}else {
				return "redirect:error";
			}
		}else {
			return "redirect:error";
		}	
	}
	@GetMapping("/user/viewPurchasePayment")
	public ModelAndView viewPurchasePayment(@ModelAttribute("command") PurchasePayment purchasePayment,BindingResult result, HttpSession session) throws RecordNotFoundException {
		Map<String,Object> model = new HashMap<String,Object>();
		User user = (User) session.getAttribute("users");
		if(user.getId() != null) {
			if(user.getId()>0) {
				model.put("purchasePayments", purchasePaymentService.getAllPurchasePayments(user.getId()));
				model.put("paymenttypes", paymentTypeService.getAllPaymentType(user.getId()));
				return new ModelAndView("purchasepayment", model);
			}else {
				return new ModelAndView("redirect:error");
			}
		}else {
			return new ModelAndView("redirect:error");
		}	
	}
	@GetMapping("/user/deletePurchasePaymentById")
	@ResponseBody
	public String deletePurchasePayment(Long pid, @ModelAttribute("command") PurchasePayment purchasePayment, BindingResult ressult) throws RecordNotFoundException {
		try {
			purchasePaymentService.deletePurchasePaymentById(purchasePayment.getPid());
			return "success";
		}catch (RecordNotFoundException e) {
			return "failed";
		}
	}
	@GetMapping("/user/getPurchasePaymentById")
	@ResponseBody
	public PurchasePayment updatePurchasePayment(Long pid, @ModelAttribute("command") PurchasePayment purchasePayment, BindingResult result) throws RecordNotFoundException {
		return purchasePaymentService.getPurchasePaymentById(purchasePayment.getPid());
	}
	@GetMapping("/purchasePayments")
	@ResponseBody
	public List<PurchasePayment> getPurchasePayments(Long userId) throws RecordNotFoundException {
		return purchasePaymentService.getAllPurchasePayments(userId);
	}
}
