package com.rajesh.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rajesh.exception.RecordNotFoundException;
import com.rajesh.model.PaymentType;
import com.rajesh.repository.PaymentTypeRepository;

@Service
public class PaymentTypeService {
	@Autowired
	private PaymentTypeRepository paymentTypeRepository;
	public PaymentType saveOrUpdatePaymentType(PaymentType paymentType) throws RecordNotFoundException {
		Optional<PaymentType> paymentT = paymentTypeRepository.findById(paymentType.getPayid());
		if(paymentT.isPresent()) {
			PaymentType newPaymentType = paymentT.get();
			newPaymentType.setPaymentMode(paymentType.getPaymentMode());
			newPaymentType = paymentTypeRepository.save(newPaymentType);
			return newPaymentType;
		}else {
			paymentType = paymentTypeRepository.save(paymentType);
			return paymentType;
		}
	}
	public List<PaymentType> getAllPaymentType(Long user_id) throws RecordNotFoundException {
		List<PaymentType> paymentTypeList = paymentTypeRepository.getAllPaymentTypeListByUserId(user_id);
		if(paymentTypeList.size() > 0) {
			return paymentTypeList;
		}else {
			return new ArrayList<PaymentType>();
		}
	}
	 public PaymentType getPaymentTypeById(Long payId) throws RecordNotFoundException {
		 Optional<PaymentType> paymentType =  paymentTypeRepository.findById(payId);
		 if(paymentType.isPresent()) {
			 return paymentType.get();
		 }else {
			 throw new RecordNotFoundException("No payment type record is exist for given id");
		 }
	 }
	 public void deletePaymentTypeById(Long payId) throws RecordNotFoundException {
		 Optional<PaymentType> paymentType = paymentTypeRepository.findById(payId);
		 if(paymentType.isPresent()) {
			 paymentTypeRepository.deleteById(payId);
		 }else {
			 throw new RecordNotFoundException("No payment type record is exist for given id");
		 }
	 }
}
