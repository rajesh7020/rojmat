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
import com.rajesh.model.PaymentType;
import com.rajesh.model.User;
import com.rajesh.service.PaymentTypeService;

@Controller
@SessionAttributes("users")
public class PaymentTypeController {
	@Autowired
	private PaymentTypeService paymentTypeService;
	
	@PostMapping("/user/savePaymentType")
	public String savePaymentType(@ModelAttribute("command")PaymentType paymentType, BindingResult result, HttpSession session) throws RecordNotFoundException {
		User user = (User) session.getAttribute("users");
		if(user.getId() != null) {
			if(user.getId()>0) {
				paymentType.setUsers(paymentType.getUsers());
				paymentTypeService.saveOrUpdatePaymentType(paymentType);
				return "redirect:/user/viewPaymentType";
			}else {
				return "redirect:error";
			}
		}else {
			return "redirect:error";
		}	
	}
	@GetMapping("/user/viewPaymentType")
	public ModelAndView viewPaymentType(@ModelAttribute("command")PaymentType paymentType, BindingResult result, HttpSession session) throws RecordNotFoundException {
		Map<String, Object> model = new HashMap<String, Object>();
		User user = (User) session.getAttribute("users");
		if(user.getId() != null) {
			if(user.getId()>0) {
				model.put("paymenttypes", paymentTypeService.getAllPaymentType(user.getId()));
				return new ModelAndView("paymenttype",model);
			}else {
				return new ModelAndView("redirect:error");
			}
		}else {
			return new ModelAndView("redirect:error");
		}	
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
	public List<PaymentType> getAllPaymentTypes(Long userId) throws RecordNotFoundException {
		return paymentTypeService.getAllPaymentType(userId);
	}
}
