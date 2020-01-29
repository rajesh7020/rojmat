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
import com.rajesh.model.Gst;
import com.rajesh.model.User;
import com.rajesh.service.GstService;

@Controller
@SessionAttributes("users")
public class GstController {
	@Autowired
	private GstService gstService;
	@PostMapping("/user/saveGst")
	public String saveGst(@ModelAttribute("command")Gst gst, BindingResult result, HttpSession session) throws RecordNotFoundException {
		User userId = (User) session.getAttribute("users");
		if(userId.getId() != null) {
			if(userId.getId()>0) {
				gst.setUsers(gst.getUsers());
				gstService.saveOrUpdateGst(gst);
				return "redirect:/user/viewGst";
			}else {
				return "redirect:error";
			}
		}else {
			return "redirect:error";
		}	
		
	}
	@GetMapping("/user/viewGst")
	public ModelAndView viewGst(@ModelAttribute("command") Gst gst, BindingResult result, HttpSession session) throws RecordNotFoundException {
		Map<String, Object> model = new HashMap<String, Object>();
		User userId = (User) session.getAttribute("users");
		if(userId.getId() != null) {
			if(userId.getId()>0) {
				model.put("gsts", gstService.getAllGsts(userId.getId()));
				return new ModelAndView("gst",model);
			}else {
				return new ModelAndView("redirect:error");
			}
		}else {
			return new ModelAndView("redirect:error");
		}	
	}
	@GetMapping("/user/getGstById")
	@ResponseBody
	public Gst updateGst(Long gstid, @ModelAttribute("command") Gst gst, BindingResult result, HttpSession session) throws RecordNotFoundException {
		return gstService.getGstById(gst.getGstid());
	}
	@GetMapping("/user/deleteGstById")
	@ResponseBody
	public String deleteGst(Long gstid, @ModelAttribute("command") Gst gst, BindingResult result, HttpSession session) throws RecordNotFoundException {
		try {
			gstService.deleteGst(gst.getGstid());
			return "success";
		}catch(Exception e) {
			return "falied";
		}
	}
	@GetMapping("/gsts")
	@ResponseBody
	public List<Gst> getAllGsts(Long userId) throws RecordNotFoundException {
		return gstService.getAllGsts(userId);
	}
}
