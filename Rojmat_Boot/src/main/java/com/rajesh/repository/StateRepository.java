package com.rajesh.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.rajesh.model.State;

@Repository
public interface StateRepository extends JpaRepository<State, Long>{
	
	  @Query(value="from State where user_id=:user_id") 
	  public List<State> getAllStateListByUserId(Long user_id);
}
