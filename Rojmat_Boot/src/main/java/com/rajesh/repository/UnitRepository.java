package com.rajesh.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.rajesh.model.Unit;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long>{
	
	  @Query(value="from Unit where user_id=:user_id") 
	  public List<Unit> getAllUnitListByUserId(Long user_id);
}
