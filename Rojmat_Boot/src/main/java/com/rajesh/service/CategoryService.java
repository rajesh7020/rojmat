package com.rajesh.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rajesh.exception.RecordNotFoundException;
import com.rajesh.model.Category;
import com.rajesh.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	CategoryRepository categoryRepository;
	
	public Category saveOrUpdateCategory(Category category) throws RecordNotFoundException {
		Optional<Category> cat = categoryRepository.findById(category.getCid());
		if(cat.isPresent()) {
			Category newCat = cat.get();
			newCat.setCategoryname(category.getCategoryname());
			newCat = categoryRepository.save(newCat);
			return newCat;
		}else {
			category = categoryRepository.save(category);
			return category;
		}
	}
	public List<Category> getAllCategories(Long userId) throws RecordNotFoundException{
		List<Category> categoryList = categoryRepository.getAllCategoryListByUserId(userId);
		if(categoryList.size() > 0) {
			return categoryList;
		}else {
			return new ArrayList<Category>();
		}
	}
	 public Category getCategoryById(Long cid) throws RecordNotFoundException {
		 Optional<Category> category =  categoryRepository.findById(cid);
		 if(category.isPresent()) {
			 return category.get();
		 }else {
			 throw new RecordNotFoundException("No category record is exist for given id");
		 }
	 }
	 public void deleteCategory(Long cid) throws RecordNotFoundException {
		 Optional<Category> category = categoryRepository.findById(cid);
		 if(category.isPresent()) {
			 categoryRepository.deleteById(cid);
		 }else {
			 throw new RecordNotFoundException("No category record is exist for given id");
		 }
	 }
}