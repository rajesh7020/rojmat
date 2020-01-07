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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.rajesh.exception.RecordNotFoundException;
import com.rajesh.model.PurchasePayment;
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
	public String savePurchasePayment(@ModelAttribute("command") PurchasePayment purchasePayment, BindingResult result) throws RecordNotFoundException {
		purchasePaymentService.saveOrUpdatePurchasePayment(purchasePayment);
		return "redirect:/user/viewPurchasePayment";
	}
	@GetMapping("/user/viewPurchasePayment")
	public ModelAndView viewPurchasePayment(@ModelAttribute("command") PurchasePayment purchasePayment,BindingResult result) {
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("purchasePayments", purchasePaymentService.getAllPurchasePayments());
		model.put("paymenttypes", paymentTypeService.getAllPaymentType());
		return new ModelAndView("purchasepayment", model);
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
	public List<PurchasePayment> getPurchasePayments() {
		return purchasePaymentService.getAllPurchasePayments();
	}
}
