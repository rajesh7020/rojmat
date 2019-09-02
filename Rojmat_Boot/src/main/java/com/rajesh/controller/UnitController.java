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
import com.rajesh.model.Unit;
import com.rajesh.service.UnitService;

@Controller
public class UnitController {
	@Autowired
	private UnitService unitService;
	
	@PostMapping("/user/saveUnit")
	public String saveUnit(@ModelAttribute("command")Unit unit, BindingResult result, HttpSession session) throws RecordNotFoundException {
		unitService.saveOrUpdateCategory(unit);
		return "redirect:/user/viewUnit";
	}
	@GetMapping("/user/viewUnit")
	public ModelAndView viewUnit(@ModelAttribute("command") Unit unit, BindingResult result, HttpSession session) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("units", unitService.getAllUnits());
		return new ModelAndView("unit",model);
	}
	@GetMapping("/user/updateUnit")
	public ModelAndView updateUnit(@ModelAttribute("command") Unit unit, BindingResult result, HttpSession session) throws RecordNotFoundException {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("unit", unitService.getUnitById(unit.getUnitid()));
		model.put("units", unitService.getAllUnits());
		return new ModelAndView("unit",model);
	}
	@GetMapping("/user/deleteUnit")
	public ModelAndView deleteUnit(@ModelAttribute("command") Unit unit, BindingResult result, HttpSession session) throws RecordNotFoundException {
		unitService.deleteUnit(unit.getUnitid());
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("units", unitService.getAllUnits());
		return new ModelAndView("redirect:/user/viewUnit");
	}
	@GetMapping("/units")
	public List<Unit> getAllUnits() {
		return unitService.getAllUnits();
	}
}
