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
import org.springframework.web.servlet.ModelAndView;
import com.rajesh.exception.RecordNotFoundException;
import com.rajesh.model.PaymentType;
import com.rajesh.service.PaymentTypeService;

@Controller
public class PaymentTypeController {
	@Autowired
	private PaymentTypeService paymentTypeService;
	
	@PostMapping("/user/savePaymentType")
	public String savePaymentType(@ModelAttribute("command")PaymentType paymentType, BindingResult result, HttpSession session) throws RecordNotFoundException {
		paymentTypeService.saveOrUpdatePaymentType(paymentType);
		return "redirect:/user/viewPaymentType";
	}
	@GetMapping("/user/viewPaymentType")
	public ModelAndView viewPaymentType(@ModelAttribute("command")PaymentType paymentType, BindingResult result, HttpSession session) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("paymenttypes", paymentTypeService.getAllPaymentType());
		return new ModelAndView("paymenttype",model);
	}
	@GetMapping("/user/updatePaymentType")
	public ModelAndView updatePaymentType(@ModelAttribute("command") PaymentType paymentType, BindingResult result, HttpSession session) throws RecordNotFoundException {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("paymenttype", paymentTypeService.getPaymentTypeById(paymentType.getPayid()));
		model.put("paymenttypes", paymentTypeService.getAllPaymentType());
		return new ModelAndView("paymenttype",model);
	}
	@GetMapping("/user/deletePaymentType")
	public ModelAndView deletePaymentType(@ModelAttribute("command")PaymentType paymentType, BindingResult result, HttpSession session) throws RecordNotFoundException {
		paymentTypeService.deletePaymentTypeById(paymentType.getPayid());
		return new ModelAndView("redirect:/user/viewPaymentType");
	}
	@GetMapping("/paymenttypes")
	public List<PaymentType> getAllPaymentTypes() {
		return paymentTypeService.getAllPaymentType();
	}
}
