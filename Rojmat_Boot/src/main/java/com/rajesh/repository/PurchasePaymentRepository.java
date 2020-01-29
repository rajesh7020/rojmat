package com.rajesh.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.rajesh.model.PurchasePayment;

@Repository
public interface PurchasePaymentRepository extends JpaRepository<PurchasePayment, Long>{
	
	  @Query(value="from PurchasePayment where user_id=:user_id") 
	  public List<PurchasePayment> getAllPurchasePaymentListByUserId(Long user_id);
}
