package com.nagarro.productDetail.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nagarro.productDetail.exception.ProductDetailNotFoundException;
import com.nagarro.productDetail.model.ProductDetail;
import com.nagarro.productDetail.repository.ProductDetailRepository;

@Service
public class ProductDetailService {

	private static final Logger logger = LoggerFactory.getLogger(ProductDetailService.class);

	@Autowired
	private ProductDetailRepository productDetailRepository;

	public ProductDetail getProductDetailByProductId(Long productId) {
		logger.info("Fetching product detail for product ID: {}", productId);
		return productDetailRepository.findByProductId(productId)
				.orElseThrow(() -> new ProductDetailNotFoundException("Product detail not found"));
	}

	public ResponseEntity<String> add(ProductDetail productDetail) {
		logger.info("Adding new product detail: {}", productDetail);
		productDetailRepository.save(productDetail);
		logger.info("Product detail saved successfully with ID: {}", productDetail.getId());
		return new ResponseEntity<>("Product added successfully", HttpStatus.CREATED);
	}

	public ResponseEntity<String> delete(Long productId) {
		logger.info("Deleting product detail with ID: {}", productId);
		if (!productDetailRepository.existsById(productId)) {
			logger.error("Product detail not found for deletion with ID: {}", productId);
			throw new ProductDetailNotFoundException("Product detail not found for deletion with ID: " + productId);
		}
		productDetailRepository.deleteById(productId);
		logger.info("Successfully deleted product detail with ID: {}", productId);
		return new ResponseEntity<>("Product Deleted Successfully", HttpStatus.NO_CONTENT);
	}
}
