package com.rajesh.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rajesh.model.Credit;

@Repository
public interface CreditDebitRepository extends  JpaRepository<Credit, Long>{
	
}
