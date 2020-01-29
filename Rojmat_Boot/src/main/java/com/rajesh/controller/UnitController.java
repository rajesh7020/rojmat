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
import com.rajesh.model.Unit;
import com.rajesh.model.User;
import com.rajesh.service.UnitService;

@Controller
@SessionAttributes("users")
public class UnitController {
	@Autowired
	private UnitService unitService;
	
	@PostMapping("/user/saveUnit")
	public String saveUnit(@ModelAttribute("command")Unit unit, BindingResult result, HttpSession session) throws RecordNotFoundException {
		User userId = (User) session.getAttribute("users");
		if(userId.getId() != null) {
			if(userId.getId()>0) {
				unit.setUsers(unit.getUsers());
				unitService.saveOrUpdateCategory(unit);
				return "redirect:/user/viewUnit";
			}else {
				return "redirect:error";
			}
		}else {
			return "redirect:error";
		}	
	}
	@GetMapping("/user/viewUnit")
	public ModelAndView viewUnit(@ModelAttribute("command") Unit unit, BindingResult result, HttpSession session) throws RecordNotFoundException {
		Map<String, Object> model = new HashMap<String, Object>();
		User userId = (User) session.getAttribute("users");
		if(userId.getId() != null) {
			if(userId.getId()>0) {
				model.put("units", unitService.getAllUnits(userId.getId()));
				return new ModelAndView("unit",model);
			}else {
				return new ModelAndView("redirect:error");
			}
		}else {
			return new ModelAndView("redirect:error");
		}	
	}
	@GetMapping("/user/getUnitById")
	@ResponseBody
	public Unit updateUnit(Long unitid, @ModelAttribute("command") Unit unit, BindingResult result, HttpSession session) throws RecordNotFoundException {
		return unitService.getUnitById(unit.getUnitid());
	}
	@GetMapping("/user/deleteUnitById")
	@ResponseBody
	public String deleteUnit(Long unitid, @ModelAttribute("command") Unit unit, BindingResult result, HttpSession session) throws RecordNotFoundException {
		try {
			unitService.deleteUnit(unit.getUnitid());
			return "success";
		}catch(Exception e) {
			return "falied";
		}
	}
	@GetMapping("/units")
	@ResponseBody
	public List<Unit> getAllUnits(Long userId) throws RecordNotFoundException {
		return unitService.getAllUnits(userId);
	}
}
