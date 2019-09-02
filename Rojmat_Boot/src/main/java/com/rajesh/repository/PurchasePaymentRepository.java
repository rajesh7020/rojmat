package com.rajesh.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rajesh.model.PurchasePayment;

@Repository
public interface PurchasePaymentRepository extends JpaRepository<PurchasePayment, Long>{

}
