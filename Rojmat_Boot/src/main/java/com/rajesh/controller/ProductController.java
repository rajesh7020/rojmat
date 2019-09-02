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
import com.rajesh.model.Product;
import com.rajesh.service.CategoryService;
import com.rajesh.service.GstService;
import com.rajesh.service.ProductService;
import com.rajesh.service.UnitService;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private UnitService unitService;
	@Autowired
	private GstService gstService;
	
	@PostMapping("/user/saveProduct")
	public String saveProduct(@ModelAttribute("command") Product product, BindingResult result) throws RecordNotFoundException {
		productService.saveOrUpdateProduct(product);
		return "redirect:/user/viewProduct";
	}
	@GetMapping("/user/viewProduct")
	public ModelAndView viewProduct(@ModelAttribute("command") Product product,BindingResult result) {
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("products", productService.getAllProducts());
		model.put("categories", categoryService.getAllCategories());
		model.put("gsts", gstService.getAllGsts());
		model.put("units", unitService.getAllUnits());
		return new ModelAndView("product", model);
	}
	@GetMapping("/user/deleteProduct")
	public ModelAndView deleteProduct(@ModelAttribute("command") Product product, BindingResult ressult) throws RecordNotFoundException {
		productService.deleteProduct(product.getPid());
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("products", productService.getAllProducts());
		model.put("categories", categoryService.getAllCategories());
		model.put("gsts", gstService.getAllGsts());
		model.put("units", unitService.getAllUnits());
		return new ModelAndView("redirect:/user/viewProduct", model);
	}
	@GetMapping("/user/updateProduct")
	public ModelAndView updateProduct(@ModelAttribute("command") Product product, BindingResult result) throws RecordNotFoundException {
		Map<String, Object> model = new HashMap<String,Object>();
		model.put("product", productService.getProductById(product.getPid()));
		model.put("products", productService.getAllProducts());
		model.put("categories", categoryService.getAllCategories());
		model.put("gsts", gstService.getAllGsts());
		model.put("units", unitService.getAllUnits());
		return new ModelAndView("product",model);
	}
	@GetMapping("/products")
	public List<Product> getProducts() {
		return productService.getAllProducts();
	}
}
