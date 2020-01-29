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
import com.rajesh.model.Product;
import com.rajesh.model.User;
import com.rajesh.service.CategoryService;
import com.rajesh.service.GstService;
import com.rajesh.service.ProductService;
import com.rajesh.service.UnitService;

@Controller
@SessionAttributes("users")
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
	public String saveProduct(@ModelAttribute("command") Product product, BindingResult result, HttpSession session) throws RecordNotFoundException {
		User user = (User) session.getAttribute("users");
		if(user.getId() != null) {
			if(user.getId()>0) {
				product.setUsers(product.getUsers());
				productService.saveOrUpdateProduct(product);
				return "redirect:/user/viewProduct";
			}else {
				return "redirect:error";
			}
		}else {
			return "redirect:error";
		}	
		
	}
	@GetMapping("/user/viewProduct")
	public ModelAndView viewProduct(@ModelAttribute("command") Product product,BindingResult result,HttpSession session) throws RecordNotFoundException {
		Map<String,Object> model = new HashMap<String,Object>();
		User user = (User) session.getAttribute("users");
		
		if(user.getId() != null) {
			if(user.getId()>0) {
				model.put("products", productService.getAllProducts(user.getId()));
				model.put("categories", categoryService.getAllCategories(user.getId()));
				model.put("gsts", gstService.getAllGsts(user.getId()));
				model.put("units", unitService.getAllUnits(user.getId()));
				return new ModelAndView("product", model);
			}else {
				return new ModelAndView("redirect:error");
			}
		}else {
			return new ModelAndView("redirect:error");
		}	
		
		
	}
	@GetMapping("/user/deleteProductById")
	@ResponseBody
	public String deleteProduct(Long pid, @ModelAttribute("command") Product product, BindingResult ressult) throws RecordNotFoundException {
		try {
			productService.deleteProduct(product.getPid());
			return "success";
		}catch(RecordNotFoundException e) {
			return "failed";
		}
	}
	@GetMapping("/user/getProductById")
	@ResponseBody
	public Product updateProduct(Long pid, @ModelAttribute("command") Product product, BindingResult result) throws RecordNotFoundException {
		return productService.getProductById(product.getPid());
	}
	@GetMapping("/products")
	@ResponseBody
	public List<Product> getProducts(Long userId) throws RecordNotFoundException {
		return productService.getAllProducts(userId);
	}
}
