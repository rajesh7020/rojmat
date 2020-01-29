package com.rajesh.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.rajesh.exception.RecordNotFoundException;
import com.rajesh.model.SalesPayment;
import com.rajesh.repository.SalesPaymentRepository;

@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class SalesPaymentService {
	@Autowired
	private SalesPaymentRepository salesPaymentRepository;
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public SalesPayment saveOrUpdateSalesPayment(SalesPayment salesPayment) throws RecordNotFoundException {
		Optional<SalesPayment> sp = salesPaymentRepository.findById(salesPayment.getSid());
		if(sp.isPresent()) {
			SalesPayment newSalesPayment = sp.get();
			newSalesPayment.setPartyName(salesPayment.getPartyName());
			newSalesPayment.setInvoiceNo(salesPayment.getInvoiceNo());
			newSalesPayment.setInvoiceAmount(salesPayment.getInvoiceAmount());
			newSalesPayment.setInvoiceDate(salesPayment.getInvoiceDate());
			newSalesPayment.setPaidAmount(salesPayment.getPaidAmount());
			newSalesPayment.setUnpaidAmount(salesPayment.getUnpaidAmount());
			newSalesPayment.setPaymentdate(salesPayment.getPaymentdate());
			newSalesPayment.setChequeNoOrTransactionId(salesPayment.getChequeNoOrTransactionId());
			newSalesPayment.setPaymenttype(salesPayment.getPaymenttype());
			newSalesPayment = salesPaymentRepository.save(newSalesPayment);
			return newSalesPayment;
		}else {
			salesPayment = salesPaymentRepository.save(salesPayment);
			return salesPayment;
		}
	}
	public List<SalesPayment> getAllSalesPayments(Long user_id) throws RecordNotFoundException {
		List<SalesPayment> salesPaymentList = salesPaymentRepository.getAllSalesPaymentListByUserId(user_id);
		if(salesPaymentList.size() > 0) {
			return salesPaymentList;
		}else {
			return new ArrayList<SalesPayment>();
		}
	}
	 public SalesPayment getSalesPaymentById(Long sid) throws RecordNotFoundException {
		 Optional<SalesPayment> salesPayment =  salesPaymentRepository.findById(sid);
		 if(salesPayment.isPresent()) {
			 return salesPayment.get();
		 }else {
			 throw new RecordNotFoundException("No sales payment record is exist for given id");
		 }
	 }
	 public void deleteSalesPaymentById(Long sid) throws RecordNotFoundException {
		 Optional<SalesPayment> salesPaymet = salesPaymentRepository.findById(sid);
		 if(salesPaymet.isPresent()) {
			 salesPaymentRepository.deleteById(sid);
		 }else {
			 throw new RecordNotFoundException("No sales payment record is exist for given id");
		 }
	 }
}
