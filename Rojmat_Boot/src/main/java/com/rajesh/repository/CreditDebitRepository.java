package com.rajesh.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.rajesh.model.Credit;

@Repository
public interface CreditDebitRepository extends  JpaRepository<Credit, Long>{
	
	
	  @Query(value="from Credit as c where c.id=:id order by date desc") 
	  public List<Credit> getAllCreditListByUserId(Long id);
	 
}
