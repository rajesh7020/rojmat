package com.rajesh.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.rajesh.model.PaymentType;

@Repository
public interface PaymentTypeRepository extends JpaRepository<PaymentType, Long>{
	
	  @Query(value="from PaymentType where user_id=:user_id") 
	  public List<PaymentType> getAllPaymentTypeListByUserId(Long user_id);
}
