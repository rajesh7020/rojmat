package com.rajesh.controller;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.rajesh.exception.RecordNotFoundException;
import com.rajesh.model.Credit;
import com.rajesh.model.Debit;
import com.rajesh.model.User;
import com.rajesh.service.CreditDebitService;
/*
 * ** author Rajesh
 */
@Controller
@SessionAttributes("users")
public class CreditDebitController {
	@Autowired
	CreditDebitService creditDebitService;
	
	@PostMapping(value="/user/savecreditdebit")
	public String saveCreditDebit(@RequestParam(value = "amount") long[] amount, @RequestParam(value = "description") String[] description, 
					ModelMap model, HttpServletRequest request, HttpSession session, @ModelAttribute("command")Credit credit, BindingResult result) {
		User userId = (User) session.getAttribute("users");
		System.out.println("uId = "+userId);
		String name = userId.getName();
		if(userId.getId() != null) {
			if(userId.getId()>0) {
				try {
					List<Debit> debits = new ArrayList<Debit>(Arrays.asList());
					debits = credit.getDebit();
					for(int i=0; i<amount.length; i++) {
						Debit debit = new Debit();
						debit.setAmount(amount[i]);
						debit.setDescription(description[i]);
						debits.add(debit);
					}   
					System.out.println("debits ="+debits);
					List<Debit> debits1 = new ArrayList<Debit>();
					
					for(int i=0; i<debits.size(); i++)
					{
						debits1.add(new Debit(debits.get(i).getAmount(),debits.get(i).getDescription()));
					}
					System.out.println("debits1 ="+debits1);
					 // Credit Data Set
						credit.setOpeningbalance(credit.getOpeningbalance());
						credit.setDate(credit.getDate());
						System.out.println("Date is: = "+credit.getDate());
						credit.setDebittotal(credit.getDebittotal());
						credit.setDrawertotal(credit.getDrawertotal());
						credit.setDebittotalplusdrawertotal(credit.getDebittotalplusdrawertotal());
						credit.setTodaybusiness(credit.getTodaybusiness());
						credit.setCreatedBy(name);
						credit.setCreatedDate(new Date());
						credit.setUpdatedBy(name);
						credit.setUpdatedDate(new Date());	
						credit.setId(userId.getId());
					//  Debit Data set	
						System.out.println("Debit List = " + debits1.size());
						System.out.println("Credit and Debit data seved successfully");
						credit.setDebit(debits1);
						creditDebitService.save(credit);
						model.put("success", "Data Saved Successfully");
				}catch(Exception e) {
					e.printStackTrace();
				}
				return "redirect:userdashboard";
			}else {
				return "redirect:error";
			}
		}else {
			return "redirect:error";
		}
		
	}
	@GetMapping(value="/user/getCreditDebitByCreditId",produces = "application/json")
	@ResponseBody
	public Credit getCreditDebitById(Long cid, Credit credit, HttpSession session, HttpServletRequest request, BindingResult result) throws RecordNotFoundException {
		return creditDebitService.getCreditDebitById(cid);
	}
	@GetMapping("user/userdashboard")
	public ModelAndView showUserAccount(ModelMap modal, @ModelAttribute("command")Credit credit, Long id, BindingResult br, HttpSession session, HttpServletRequest request) throws RecordNotFoundException {
		//Long userId = Long.parseLong(request.getParameter("userId"));
		User user = (User)session.getAttribute("users");
		Date lastAccessTime = new Date(session.getLastAccessedTime());
		Map<String,Object> model = new HashMap<String,Object>();
		if(user.getId() != null) {
			modal.put("creditdebitlist", creditDebitService.getAllCreditListByUserId(user.getId()));
			modal.addAttribute("lastAccessTime", lastAccessTime);
			return new ModelAndView("userdashboard",model);
		}else {
			return new ModelAndView("error");
		}
		
		
	}

	
	  @SuppressWarnings("unchecked")
	  @GetMapping("/user/creditDebitList")
	  @ResponseBody
	  public List<Credit> showCreditDebitList(Long userId) throws RecordNotFoundException {
		  return (List<Credit>) creditDebitService.getAllCreditListByUserId(userId);
	}
	 
	@GetMapping(value="/user/deleteCreditDebitByCreditId")
	@ResponseBody
	public String deleteCreditDebitById(Long cid, Credit credit, HttpSession session, HttpServletRequest request, BindingResult result) throws RecordNotFoundException {
		try {
			creditDebitService.deleteCreditDebitById(cid);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	@GetMapping(value="/user/viewCustomerInvoice")
	public ModelAndView showCustomerInvoice(HttpSession session, HttpServletRequest request) {
		return  new ModelAndView("customer_invoice");
	}
	@GetMapping(value="/user/viewSupplierInvoice")
	public ModelAndView showSupplierInvoice(HttpSession session, HttpServletRequest request) {
		return new ModelAndView("supplier_invoice");
	}
}