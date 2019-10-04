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
import com.rajesh.model.Supplier;
import com.rajesh.service.StateService;
import com.rajesh.service.SupplierService;

@Controller
public class SupplierController {
	@Autowired
	private SupplierService supplierService;
	@Autowired
	private StateService stateService;
	@PostMapping("/user/saveSupplier")
	public String saveSupplier(@ModelAttribute("command") Supplier supplier, BindingResult result) throws RecordNotFoundException {
		supplierService.saveOrUpdateSupplier(supplier);
		return "redirect:/user/viewSupplier";
	}
	@GetMapping("/user/viewSupplier")
	public ModelAndView viewSupplier(@ModelAttribute("command") Supplier supplier,BindingResult result) {
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("suppliers", supplierService.getAllSuppliers());
		model.put("states", stateService.getAllStates());
		return new ModelAndView("supplier", model);
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
	public List<Supplier> getSuppliers() {
		return supplierService.getAllSuppliers();
	}
}
