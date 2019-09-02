package com.rajesh.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rajesh.model.SalesPayment;

@Repository
public interface SalesPaymentRepository extends JpaRepository<SalesPayment, Long>{

}
