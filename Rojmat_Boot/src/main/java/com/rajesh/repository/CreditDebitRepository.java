package com.rajesh.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.rajesh.model.Credit;

@Repository
public interface CreditDebitRepository extends  CrudRepository<Credit, Long>{

}
