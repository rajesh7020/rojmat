package com.rajesh.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.rajesh.exception.RecordNotFoundException;
import com.rajesh.model.Product;
import com.rajesh.repository.ProductRepository;

@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Product saveOrUpdateProduct(Product product) throws RecordNotFoundException {
		Optional<Product> p = productRepository.findById(product.getPid());
		if(p.isPresent()) {
			Product newProduct = p.get();
			newProduct.setName(product.getName());
			newProduct.setHsncode(product.getHsncode());
			newProduct.setPrice(product.getPrice());
			newProduct.setCategory(product.getCategory());
			newProduct.setGst(product.getGst());
			newProduct.setUnit(product.getUnit());
			newProduct.setSgst(product.getSgst());
			newProduct.setCgst(product.getCgst());
			newProduct.setIgst(product.getIgst());
			newProduct = productRepository.save(newProduct);
			return newProduct;
		}else {
			product = productRepository.save(product);
			return product;
		}
	}
	public List<Product> getAllProducts(Long user_id) throws RecordNotFoundException {
		List<Product> productList = productRepository.getAllProductListByUserId(user_id);
		if(productList.size() > 0) {
			return productList;
		}else {
			return new ArrayList<Product>();
		}
	}
	 public Product getProductById(Long pid) throws RecordNotFoundException {
		 Optional<Product> product =  productRepository.findById(pid);
		 if(product.isPresent()) {
			 return product.get();
		 }else {
			 throw new RecordNotFoundException("No product record is exist for given id");
		 }
	 }
	 public void deleteProduct(Long pid) throws RecordNotFoundException {
		 Optional<Product> product = productRepository.findById(pid);
		 if(product.isPresent()) {
			 productRepository.deleteById(pid);
		 }else {
			 throw new RecordNotFoundException("No product record is exist for given id");
		 }
	 }
}
