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
		List<Credit> data = (List<Credit>) creditDebitRepository.findAll();
		System.out.println(data);
		return (List<Credit>) creditDebitRepository.findAll();
	}

	
	public List<Credit> getAllCreditListByUserId(Long userId) {
		List<Credit> data = creditDebitRepository.getAllCreditListByUserId(userId);
		System.out.println("Credit List "+data); 
		return data; 
	}
	 
	public Credit getCreditDebitById(long cid) throws RecordNotFoundException {
		Optional<Credit> credit =  creditDebitRepository.findById(cid);
		if(credit.isPresent()) {
			return credit.get();
		 }else {
			 throw new RecordNotFoundException("No Credit debit record is exist for given id");
		 }
	}
	public void deleteCreditDebitById(long cid) throws RecordNotFoundException {
		Optional<Credit> credit =  creditDebitRepository.findById(cid);
		if(credit.isPresent())
        {
			creditDebitRepository.deleteById(cid);
        } else {
            throw new RecordNotFoundException("No Credit Debit record exist for given cid");
        }
	}
	
}
