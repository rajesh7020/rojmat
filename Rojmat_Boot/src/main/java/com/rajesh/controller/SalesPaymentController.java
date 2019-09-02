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
import org.springframework.web.servlet.ModelAndView;
import com.rajesh.exception.RecordNotFoundException;
import com.rajesh.model.SalesPayment;
import com.rajesh.service.PaymentTypeService;
import com.rajesh.service.SalesPaymentService;

@Controller
public class SalesPaymentController {
	@Autowired
	private SalesPaymentService salesPaymentService;
	@Autowired
	private PaymentTypeService paymentTypeService;
	@PostMapping("/user/saveSalesPayment")
	public String saveSalesPayment(@ModelAttribute("command") SalesPayment salesPayment, BindingResult result) throws RecordNotFoundException {
		salesPaymentService.saveOrUpdateSalesPayment(salesPayment);
		return "redirect:/user/viewSalesPayment";
	}
	@GetMapping("/user/viewSalesPayment")
	public ModelAndView viewSalesPayment(@ModelAttribute("command") SalesPayment salesPayment,BindingResult result) {
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("salesPayments", salesPaymentService.getAllSalesPayments());
		model.put("paymenttypes", paymentTypeService.getAllPaymentType());
		return new ModelAndView("salespayment", model);
	}
	@GetMapping("/user/deleteSalesPayment")
	public ModelAndView deleteSalesPayments(@ModelAttribute("command") SalesPayment salesPayment, BindingResult ressult) throws RecordNotFoundException {
		salesPaymentService.deleteSalesPaymentById(salesPayment.getSid());
		return new ModelAndView("redirect:/user/viewSalesPayment");
	}
	@GetMapping("/user/updateSalesPayment")
	public ModelAndView updateSalesPayment(@ModelAttribute("command") SalesPayment salesPayment, BindingResult result) throws RecordNotFoundException {
		Map<String, Object> model = new HashMap<String,Object>();
		model.put("salesPayment", salesPaymentService.getSalesPaymentById(salesPayment.getSid()));
		model.put("salesPayments", salesPaymentService.getAllSalesPayments());
		model.put("paymenttypes", paymentTypeService.getAllPaymentType());
		return new ModelAndView("salespayment",model);
	}
	@GetMapping("/salesPayments")
	public List<SalesPayment> getSalesPayments() {
		return salesPaymentService.getAllSalesPayments();
	}
}
