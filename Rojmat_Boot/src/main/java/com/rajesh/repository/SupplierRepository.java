package com.rajesh.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.rajesh.model.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long>{
	
	  @Query(value="from Supplier where user_id=:user_id") 
	  public List<Supplier> getAllSupplierListByUserId(Long user_id);
}
