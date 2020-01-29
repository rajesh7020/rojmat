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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.rajesh.exception.RecordNotFoundException;
import com.rajesh.model.State;
import com.rajesh.model.User;
import com.rajesh.service.StateService;

@Controller
@SessionAttributes("users")
public class StateController {
	@Autowired
	private StateService stateService;
	@PostMapping("/user/saveState")
	public String saveState(@ModelAttribute("command")State state, BindingResult result, HttpSession session) throws RecordNotFoundException {
		User user = (User) session.getAttribute("users");
		if(user.getId() != null) {
			if(user.getId()>0) {
				state.setUsers(state.getUsers());
				stateService.saveOrUpdateState(state);
				return "redirect:/user/viewState";
			}else {
				return "redirect:error";
			}
		}else {
			return "redirect:error";
		}	
	}
	@GetMapping("/user/viewState")
	public ModelAndView viewState(@ModelAttribute("command") State state, BindingResult result, HttpSession session) throws RecordNotFoundException {
		Map<String, Object> model = new HashMap<String, Object>();
		User user = (User) session.getAttribute("users");
		if(user.getId() != null) {
			if(user.getId()>0) {
				model.put("states", stateService.getAllStates(user.getId()));
				return new ModelAndView("states",model);
			}else {
				return new ModelAndView("redirect:error");
			}
		}else {
			return new ModelAndView("redirect:error");
		}	
	}
	@GetMapping("/user/updateState")
	public ModelAndView updateState(@ModelAttribute("command") State state, BindingResult result, HttpSession session) throws RecordNotFoundException {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("state", stateService.getStateById(state.getStateid()));
		return new ModelAndView("states",model);
	}
	@GetMapping("/user/deleteState")
	public ModelAndView deleteState(@ModelAttribute("command") State state, BindingResult result, HttpSession session) throws RecordNotFoundException {
		stateService.deleteState(state.getStateid());
		return new ModelAndView("redirect:/user/viewState");
	}
	@GetMapping("/states")
	public List<State> getAllStates(Long userId) throws RecordNotFoundException {
		return stateService.getAllStates(userId);
	}
}
