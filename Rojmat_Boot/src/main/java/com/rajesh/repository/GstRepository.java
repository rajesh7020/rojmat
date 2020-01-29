package com.rajesh.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.rajesh.model.Gst;

@Repository
public interface GstRepository extends JpaRepository<Gst, Long>{
	
	  @Query(value="from Gst where user_id=:user_id") 
	  public List<Gst> getAllGstListByUserId(Long user_id);
}
