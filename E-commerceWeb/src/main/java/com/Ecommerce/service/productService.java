package com.Ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ecommerce.model.Product;
import com.Ecommerce.repository.ProductRepository;
@Service
public class productService {
	@Autowired
	ProductRepository productRepository;

	public List<Product> getAllProduct(){
		return productRepository.findAll();
	}
	public void addProducts(Product product) {
		productRepository.save(product);
	}
	public void removeProducById(long id) {
		productRepository.deleteById(id);
	}
	public Optional<Product> getProductById(long id){
		return productRepository.findById(id);
	}
	public List<Product> getAllProductByCategoryId(int id){
		return productRepository.findAllByCategory_Id(id);
	}
}
