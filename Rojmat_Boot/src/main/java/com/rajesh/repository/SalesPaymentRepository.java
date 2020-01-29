package com.rajesh.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.rajesh.model.SalesPayment;

@Repository
public interface SalesPaymentRepository extends JpaRepository<SalesPayment, Long>{
	
	  @Query(value="from SalesPayment where user_id=:user_id") 
	  public List<SalesPayment> getAllSalesPaymentListByUserId(Long user_id);
}
