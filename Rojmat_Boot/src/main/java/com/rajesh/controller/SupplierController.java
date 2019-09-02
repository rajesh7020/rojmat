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
	@GetMapping("/user/deleteSupplier")
	public ModelAndView deleteSupplier(@ModelAttribute("command") Supplier supplier, BindingResult ressult) throws RecordNotFoundException {
		supplierService.deleteSupplierById(supplier.getSupid());
		return new ModelAndView("redirect:/user/viewSupplier");
	}
	@GetMapping("/user/updateSupplier")
	public ModelAndView updateSupplier(@ModelAttribute("command") Supplier supplier, BindingResult result) throws RecordNotFoundException {
		Map<String, Object> model = new HashMap<String,Object>();
		model.put("supplier", supplierService.getSupplierById(supplier.getSupid()));
		model.put("suppliers", supplierService.getAllSuppliers());
		model.put("states", stateService.getAllStates());
		return new ModelAndView("supplier",model);
	}
	@GetMapping("/suppliers")
	public List<Supplier> getSuppliers() {
		return supplierService.getAllSuppliers();
	}
}
