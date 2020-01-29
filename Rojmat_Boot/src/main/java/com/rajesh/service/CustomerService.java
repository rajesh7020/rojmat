package com.rajesh.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.rajesh.exception.RecordNotFoundException;
import com.rajesh.model.Customer;
import com.rajesh.repository.CustomerRepository;

@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Customer saveOrUpdateCustomer(Customer customer) throws RecordNotFoundException {
		Optional<Customer> cs = customerRepository.findById(customer.getCustid());
		if(cs.isPresent()) {
			Customer newCustomer = cs.get();
			newCustomer.setCustomername(customer.getCustomername());
			newCustomer.setEmail(customer.getEmail());
			newCustomer.setAddress(customer.getAddress());
			newCustomer.setCity(customer.getCity());
			newCustomer.setGstinno(customer.getGstinno());
			newCustomer.setMobileno(customer.getMobileno());
			newCustomer.setPanno(customer.getPanno());
			newCustomer.setPincode(customer.getPincode());
			newCustomer.setState(customer.getState());
			newCustomer.setBankname(customer.getBankname());
			newCustomer.setBranchname(customer.getBranchname());
			newCustomer.setAccountno(customer.getAccountno());
			newCustomer.setIfscode(customer.getIfscode());
			newCustomer = customerRepository.save(newCustomer);
			return newCustomer;
		}else {
			customer = customerRepository.save(customer);
			return customer;
		}
	}
	public List<Customer> getAllCustomers(Long user_id) throws RecordNotFoundException {
		List<Customer> customerList = customerRepository.getAllCustomerListByUserId(user_id);
		if(customerList.size() > 0) {
			return customerList;
		}else {
			return new ArrayList<Customer>();
		}
	}
	 public Customer getCustomerById(Long custid) throws RecordNotFoundException {
		 Optional<Customer> customer =  customerRepository.findById(custid);
		 if(customer.isPresent()) {
			 return customer.get();
		 }else {
			 throw new RecordNotFoundException("No customer payment record is exist for given id");
		 }
	 }
	 public void deleteCustomerById(Long custid) throws RecordNotFoundException {
		 Optional<Customer> customer = customerRepository.findById(custid);
		 if(customer.isPresent()) {
			 customerRepository.deleteById(custid);
		 }else {
			 throw new RecordNotFoundException("No customer payment record is exist for given id");
		 }
	 }
}
