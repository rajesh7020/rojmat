package com.rajesh.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rajesh.exception.RecordNotFoundException;
import com.rajesh.model.Credit;
import com.rajesh.repository.CreditDebitRepository;

@Service
public class CreditDebitService {
	@Autowired
	CreditDebitRepository creditDebitRepository;
	
	public void save(Credit credit) {
		creditDebitRepository.save(credit);
	}
	public List<Credit> getAllCreditList() {
		return (List<Credit>) creditDebitRepository.findAll();
	}
	public Credit getCreditDebitById(long cid) throws RecordNotFoundException {
		Optional<Credit> credit =  creditDebitRepository.findById(cid);
		if(credit.isPresent()) {
			return credit.get();
		 }else {
			 throw new RecordNotFoundException("No Credit debit record is exist for given id");
		 }
		
	}
}
