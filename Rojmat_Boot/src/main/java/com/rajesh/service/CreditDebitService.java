package com.rajesh.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rajesh.model.Credit;
import com.rajesh.repository.CreditDebitRepository;

@Service
public class CreditDebitService {
	@Autowired
	CreditDebitRepository creditDebitRepository;
	
	public void save(Credit credit) {
		creditDebitRepository.save(credit);
	}
}
