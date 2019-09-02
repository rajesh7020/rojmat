package com.rajesh.controller;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.rajesh.model.Credit;
import com.rajesh.model.Debit;
import com.rajesh.model.User;
import com.rajesh.service.CreditDebitService;

@Controller
public class CreditDebitController {
	@Autowired
	CreditDebitService creditDebitService;
	
	@PostMapping("/user/savecreditdebit")
	public String saveCreditDebit(@RequestParam(value = "amount") String[] amount, 
            @RequestParam(value = "description") String[] description, 
            ModelMap model, HttpServletRequest request, HttpSession session,@ModelAttribute("command")Credit credit, BindingResult result) {
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
			User user = new User();
			 // Credit Data Set
				credit.setOpeningbalance(credit.getOpeningbalance());
				credit.setDate(credit.getDate());
				credit.setDebittotal(credit.getDebittotal());
				credit.setDrawertotal(credit.getDrawertotal());
				credit.setDebittotalplusdrawertotal(credit.getDebittotalplusdrawertotal());
				credit.setTodaybusiness(credit.getTodaybusiness());
				credit.setCreatedBy(user.getEmail());
				credit.setCreatedDate(new Date());
				credit.setUpdatedBy(user.getEmail());
				credit.setUpdatedDate(new Date());	
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
	}
}
