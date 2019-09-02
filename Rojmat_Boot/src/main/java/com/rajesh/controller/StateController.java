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
import com.rajesh.model.State;
import com.rajesh.service.StateService;

@Controller
public class StateController {
	@Autowired
	private StateService stateService;
	@PostMapping("/user/saveState")
	public String saveState(@ModelAttribute("command")State state, BindingResult result, HttpSession session) throws RecordNotFoundException {
		stateService.saveOrUpdateState(state);
		return "redirect:/user/viewState";
	}
	@GetMapping("/user/viewState")
	public ModelAndView viewState(@ModelAttribute("command") State state, BindingResult result, HttpSession session) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("states", stateService.getAllStates());
		return new ModelAndView("states",model);
	}
	@GetMapping("/user/updateState")
	public ModelAndView updateState(@ModelAttribute("command") State state, BindingResult result, HttpSession session) throws RecordNotFoundException {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("state", stateService.getStateById(state.getStateid()));
		model.put("states", stateService.getAllStates());
		return new ModelAndView("states",model);
	}
	@GetMapping("/user/deleteState")
	public ModelAndView deleteState(@ModelAttribute("command") State state, BindingResult result, HttpSession session) throws RecordNotFoundException {
		stateService.deleteState(state.getStateid());
		return new ModelAndView("redirect:/user/viewState");
	}
	@GetMapping("/states")
	public List<State> getAllStates() {
		return stateService.getAllStates();
	}
}
