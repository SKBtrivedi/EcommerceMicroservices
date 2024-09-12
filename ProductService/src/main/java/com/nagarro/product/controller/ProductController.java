package com.nagarro.product.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nagarro.product.dto.ProductResponse;
import com.nagarro.product.model.Product;
import com.nagarro.product.service.ProductService;

/**
 * ProductController handles all operations related to products, including
 * adding, removing, and retrieving product details. It communicates with other
 * microservices to get additional details such as product pricing and design.
 */
@RestController
@RequestMapping("/product")
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	/**
	 * Fetches product details along with additional information such as price and
	 * size. Communicates with ProductDetailService and PriceService for extra data.
	 * 
	 * @param id - The ID of the product to retrieve.
	 * @return ResponseEntity<ProductResponse> - Returns the product details with
	 *         HTTP status 200.
	 */
	@Scope("read:products")
	@GetMapping("/{id}/details")
	public ResponseEntity<ProductResponse> getProductWithDetails(@PathVariable Long id) {
		logger.info("Fetching product details for ID: {}", id);
		ProductResponse productDetails = productService.getProductWithDetails(id);
		return ResponseEntity.ok(productDetails);
	}

	/**
	 * Adds a new product to the inventory.
	 * 
	 * @param product - The product object containing the details of the product.
	 * @return ResponseEntity<Product> - Returns the added product with HTTP status
	 *         200.
	 */
	@Scope("write:products")
	@PostMapping("/add")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		logger.info("Adding product: {}", product.getName());
		Product addedProduct = productService.addProduct(product);
		return ResponseEntity.ok(addedProduct);
	}

	/**
	 * Removes an existing product from the inventory based on the provided product
	 * ID.
	 * 
	 * @param id - The ID of the product to be removed.
	 * @return ResponseEntity<String> - Returns a success message upon successful
	 *         removal.
	 */
	@Scope("write:products")
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> removeProduct(@PathVariable Long id) {
		logger.info("Removing product with ID: {}", id);
		productService.removeProduct(id);
		return ResponseEntity.ok("Product removed successfully");
	}

	/**
	 * Retrieves all products available in the inventory.
	 * 
	 * @return ResponseEntity<List<Product>> - Returns a list of all products with
	 *         HTTP status 200.
	 */
	@Scope("read:products")
	@GetMapping("/all")
	public ResponseEntity<List<Product>> getAllProducts() {
		logger.debug("Fetching all products");
		List<Product> products = productService.getAllProducts();
		return ResponseEntity.ok(products);
	}
}
