package com.rajesh.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.rajesh.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	  @Query(value="from Customer where user_id=:user_id") 
	  public List<Customer> getAllCustomerListByUserId(Long user_id);
}
