package com.rajesh.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.rajesh.exception.RecordNotFoundException;
import com.rajesh.model.PurchasePayment;
import com.rajesh.repository.PurchasePaymentRepository;

@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class PurchasePaymentService {
	@Autowired
	private PurchasePaymentRepository purchasePaymentRepository;
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public PurchasePayment saveOrUpdatePurchasePayment(PurchasePayment purchasePayment) throws RecordNotFoundException {
		Optional<PurchasePayment> pp = purchasePaymentRepository.findById(purchasePayment.getPid());
		if(pp.isPresent()) {
			PurchasePayment newPurchasePayment = pp.get();
			newPurchasePayment.setPartyName(purchasePayment.getPartyName());
			newPurchasePayment.setInvoiceNo(purchasePayment.getInvoiceNo());
			newPurchasePayment.setInvoiceAmount(purchasePayment.getInvoiceAmount());
			newPurchasePayment.setInvoiceDate(purchasePayment.getInvoiceDate());
			newPurchasePayment.setPaidAmount(purchasePayment.getPaidAmount());
			newPurchasePayment.setUnpaidAmount(purchasePayment.getUnpaidAmount());
			newPurchasePayment.setPaymentdate(purchasePayment.getPaymentdate());
			newPurchasePayment.setChequeNoOrTransactionId(purchasePayment.getChequeNoOrTransactionId());
			newPurchasePayment.setPaymenttype(purchasePayment.getPaymenttype());
			newPurchasePayment = purchasePaymentRepository.save(purchasePayment);
			return newPurchasePayment;
		}else {
			purchasePayment = purchasePaymentRepository.save(purchasePayment);
			return purchasePayment;
		}
	}
	public List<PurchasePayment> getAllPurchasePayments() {
		List<PurchasePayment> purchasePaymentList = purchasePaymentRepository.findAll();
		if(purchasePaymentList.size() > 0) {
			return purchasePaymentList;
		}else {
			return new ArrayList<PurchasePayment>();
		}
	}
	 public PurchasePayment getPurchasePaymentById(Long pid) throws RecordNotFoundException {
		 Optional<PurchasePayment> purchasePayment =  purchasePaymentRepository.findById(pid);
		 if(purchasePayment.isPresent()) {
			 return purchasePayment.get();
		 }else {
			 throw new RecordNotFoundException("No purchase payment record is exist for given id");
		 }
	 }
	 public void deletePurchasePaymentById(Long pid) throws RecordNotFoundException {
		 Optional<PurchasePayment> purchasePayment = purchasePaymentRepository.findById(pid);
		 if(purchasePayment.isPresent()) {
			 purchasePaymentRepository.deleteById(pid);
		 }else {
			 throw new RecordNotFoundException("No purchase payment record is exist for given id");
		 }
	 }
}
