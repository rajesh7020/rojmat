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
import com.rajesh.model.Gst;
import com.rajesh.service.GstService;

@Controller
public class GstController {
	@Autowired
	private GstService gstService;
	@PostMapping("/user/saveGst")
	public String saveGst(@ModelAttribute("command")Gst gst, BindingResult result, HttpSession session) throws RecordNotFoundException {
		gstService.saveOrUpdateGst(gst);
		return "redirect:/user/viewGst";
	}
	@GetMapping("/user/viewGst")
	public ModelAndView viewGst(@ModelAttribute("command") Gst gst, BindingResult result, HttpSession session) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("gsts", gstService.getAllGsts());
		return new ModelAndView("gst",model);
	}
	@GetMapping("/user/updateGst")
	public ModelAndView updateGst(@ModelAttribute("command") Gst gst, BindingResult result, HttpSession session) throws RecordNotFoundException {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("gst", gstService.getGstById(gst.getGstid()));
		model.put("gsts", gstService.getAllGsts());
		return new ModelAndView("gst",model);
	}
	@GetMapping("/user/deleteGst")
	public ModelAndView deleteGst(@ModelAttribute("command") Gst gst, BindingResult result, HttpSession session) throws RecordNotFoundException {
		gstService.deleteGst(gst.getGstid());
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("gsts", gstService.getAllGsts());
		return new ModelAndView("redirect:/user/viewGst");
	}
	@GetMapping("/gsts")
	public List<Gst> getAllGsts() {
		return gstService.getAllGsts();
	}
}
