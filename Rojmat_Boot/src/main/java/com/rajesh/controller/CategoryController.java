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
import com.rajesh.model.Category;
import com.rajesh.service.CategoryService;

@Controller
public class CategoryController {
	@Autowired
	CategoryService categoryService;
	@PostMapping(value="/user/savecategory",produces = "application/json")
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
	@GetMapping(value = "/user/getCetagoryById",produces = "application/json")
	@ResponseBody
	public Category updateCategory(Long cid, Category category, BindingResult result, HttpSession session) throws RecordNotFoundException {
		return categoryService.getCategoryById(category.getCid());
	}
	@GetMapping(value="/user/deleteCategoryById")
	@ResponseBody
	public String deleteCategory(Long cid, Category category, BindingResult result, HttpSession session) throws RecordNotFoundException {
		try {
			categoryService.deleteCategory(category.getCid());
			return "success";
		}catch(Exception e) {
			return "falied";
		}
		
	}
	@GetMapping("/categories")
	@ResponseBody
	public List<Category> getAllCategries() {
		return categoryService.getAllCategories();
	}
}