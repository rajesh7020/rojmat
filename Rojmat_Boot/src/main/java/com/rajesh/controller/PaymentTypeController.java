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
	@GetMapping("/user/getPaymentTypeById")
	@ResponseBody
	public PaymentType updatePaymentType(Long payid, @ModelAttribute("command") PaymentType paymentType, BindingResult result, HttpSession session) throws RecordNotFoundException {
		return paymentTypeService.getPaymentTypeById(paymentType.getPayid());
	}
	@GetMapping("/user/deletePaymentTypeById")
	@ResponseBody
	public String deletePaymentType(Long payid,@ModelAttribute("command")PaymentType paymentType, BindingResult result, HttpSession session) throws RecordNotFoundException {
		try {
			paymentTypeService.deletePaymentTypeById(paymentType.getPayid());
			return "success";
		}catch (RecordNotFoundException e) {
			return "failed";
		}
	}
	@GetMapping("/paymenttypes")
	@ResponseBody
	public List<PaymentType> getAllPaymentTypes() {
		return paymentTypeService.getAllPaymentType();
	}
}
