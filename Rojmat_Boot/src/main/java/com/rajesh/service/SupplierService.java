package com.rajesh.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.rajesh.exception.RecordNotFoundException;
import com.rajesh.model.Supplier;
import com.rajesh.repository.SupplierRepository;

@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class SupplierService {
	@Autowired
	private SupplierRepository supplierRepository;
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Supplier saveOrUpdateSupplier(Supplier supplier) throws RecordNotFoundException {
		Optional<Supplier> s = supplierRepository.findById(supplier.getSupid());
		if(s.isPresent()) {
			Supplier newSupplier = s.get();
			newSupplier.setSuppliername(supplier.getSuppliername());
			newSupplier.setEmail(supplier.getEmail());
			newSupplier.setAddress(supplier.getAddress());
			newSupplier.setCity(supplier.getCity());
			newSupplier.setGstinno(supplier.getGstinno());
			newSupplier.setMobileno(supplier.getMobileno());
			newSupplier.setPanno(supplier.getPanno());
			newSupplier.setPincode(supplier.getPincode());
			newSupplier.setState(supplier.getState());
			newSupplier.setBankname(supplier.getBankname());
			newSupplier.setBranchname(supplier.getBranchname());
			newSupplier.setAccountno(supplier.getAccountno());
			newSupplier.setIfscode(supplier.getIfscode());
			newSupplier = supplierRepository.save(newSupplier);
			return newSupplier;
		}else {
			supplier = supplierRepository.save(supplier);
			return supplier;
		}
	}
	public List<Supplier> getAllSuppliers() {
		List<Supplier> supplierList = supplierRepository.findAll();
		if(supplierList.size() > 0) {
			return supplierList;
		}else {
			return new ArrayList<Supplier>();
		}
	}
	 public Supplier getSupplierById(Long supId) throws RecordNotFoundException {
		 Optional<Supplier> supplier =  supplierRepository.findById(supId);
		 if(supplier.isPresent()) {
			 return supplier.get();
		 }else {
			 throw new RecordNotFoundException("No supplier payment record is exist for given id");
		 }
	 }
	 public void deleteSupplierById(Long supId) throws RecordNotFoundException {
		 Optional<Supplier> supplier = supplierRepository.findById(supId);
		 if(supplier.isPresent()) {
			 supplierRepository.deleteById(supId);
		 }else {
			 throw new RecordNotFoundException("No supplier payment record is exist for given id");
		 }
	 }
}
