package com.rajesh.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.rajesh.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
	
	  @Query(value="from Category where user_id=:user_id") 
	  public List<Category> getAllCategoryListByUserId(Long user_id);
}
