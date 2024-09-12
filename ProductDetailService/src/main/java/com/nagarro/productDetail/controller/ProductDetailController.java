package com.nagarro.productDetail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.productDetail.model.ProductDetail;
import com.nagarro.productDetail.service.ProductDetailService;

/**
 * ProductDetailController manages product detail information such as size,
 * design, etc. It exposes endpoints for adding, retrieving, and deleting
 * product details.
 */
@RestController
@RequestMapping("/product-detail")
public class ProductDetailController {

	@Autowired
	private ProductDetailService productDetailService;

	/**
	 * Retrieves product details based on the productId.
	 * 
	 * @param productId - The ID of the product to retrieve details for.
	 * @return ResponseEntity<ProductDetail> - Returns the product details with HTTP
	 *         status 200.
	 */
	@Scope("read:products")
	@GetMapping("/{productId}")
	public ResponseEntity<ProductDetail> getProductDetailByProductId(@PathVariable Long productId) {
		ProductDetail detail = productDetailService.getProductDetailByProductId(productId);
		return new ResponseEntity<>(detail, HttpStatus.OK);
	}

	/**
	 * Adds new product details to the system.
	 * 
	 * @param productDetail - The product detail object containing size, design,
	 *                      etc.
	 * @return ResponseEntity<String> - Returns a success message with HTTP status
	 *         201.
	 */
	@Scope("write:products")
	@PostMapping("/add")
	public ResponseEntity<String> addProductDetails(@RequestBody ProductDetail productDetail) {
		return productDetailService.add(productDetail);

	}

	/**
	 * Deletes product details based on productId.
	 * 
	 * @param productId - The ID of the product whose details need to be deleted.
	 * @return ResponseEntity<String> - Returns a success message upon deletion.
	 */
	@Scope("write:products")
	@DeleteMapping("/delete/{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
		return productDetailService.delete(productId);
	}
}
