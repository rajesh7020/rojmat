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
import com.rajesh.model.Category;
import com.rajesh.service.CategoryService;

@Controller
public class CategoryController {
	@Autowired
	CategoryService categoryService;
	@PostMapping("/user/savecategory")
	public String saveCategory(@ModelAttribute("command")Category category, BindingResult result, HttpSession session) throws RecordNotFoundException {
		categoryService.saveOrUpdateCategory(category);
		return "redirect:/user/viewCategory";
	}
	@GetMapping("/user/viewCategory")
	public ModelAndView viewCategory(@ModelAttribute("command") Category category, BindingResult result, HttpSession session) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("categories", categoryService.getAllCategories());
		return new ModelAndView("category",model);
	}
	@GetMapping("/user/updateCategory")
	public ModelAndView updateCategory(@ModelAttribute("command") Category category, BindingResult result, HttpSession session) throws RecordNotFoundException {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("category", categoryService.getCategoryById(category.getCid()));
		model.put("categories", categoryService.getAllCategories());
		return new ModelAndView("category",model);
	}
	@GetMapping("/user/deleteCategory")
	public ModelAndView deleteCategory(@ModelAttribute("command") Category category, BindingResult result, HttpSession session) throws RecordNotFoundException {
		categoryService.deleteCategory(category.getCid());
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("categories", categoryService.getAllCategories());
		return new ModelAndView("redirect:/user/viewCategory");
		
	}
	@GetMapping("/categories")
	public List<Category> getAllCategries() {
		return categoryService.getAllCategories();
	}
}