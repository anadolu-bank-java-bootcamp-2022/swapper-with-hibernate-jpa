package com.gokhantamkoc.javabootcamp.swapperwithhibernatejpa.service;

import java.util.List;

import lombok.Data;
import org.springframework.stereotype.Service;

import com.gokhantamkoc.javabootcamp.swapperwithhibernatejpa.model.Product;
import com.gokhantamkoc.javabootcamp.swapperwithhibernatejpa.repository.ProductRepository;

@Service
@Data
public class ProductService {

	private ProductRepository productRepository;

	public Integer create(Product newProduct) {
		return productRepository.createProduct(newProduct);
	}

	public Product get(int id) throws Exception {
		return productRepository.getProduct(id);
	}

	public List<Product> list() {
		return productRepository.getAllProduct();
	}

	public boolean updateById(Product updatedProduct) {
		return productRepository.updateProductById(updatedProduct);
	}
}
