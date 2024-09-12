package com.nagarro.product.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nagarro.product.dto.Price;
import com.nagarro.product.dto.ProductDetail;
import com.nagarro.product.dto.ProductResponse;
import com.nagarro.product.exception.ProductNotFoundException;
import com.nagarro.product.feign.PriceClient;
import com.nagarro.product.feign.ProductDetailClient;
import com.nagarro.product.model.Product;
import com.nagarro.product.repository.ProductRepository;

/**
 * ProductService handles the core business logic for managing products. It
 * communicates with other microservices to fetch additional product details
 * like pricing and size.
 */
@Service
public class ProductService {

	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private PriceClient priceClient;

	@Autowired
	private ProductDetailClient productDetailClient;

	/**
	 * Fetches product details along with price and size by communicating with other
	 * services.
	 * 
	 * @param id - The ID of the product.
	 * @return ProductResponse - The detailed product response.
	 */
	public ProductResponse getProductWithDetails(Long productId) {

		logger.debug("Fetching product details for ID: {}", productId);
		// Get the product
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ProductNotFoundException("Product not found"));

		// Fetch price and product details using Feign clients
		Price price = priceClient.getPriceByProductId(productId);
		ProductDetail productDetail = productDetailClient.getProductDetailByProductId(productId);

		// Return a composite response
		return new ProductResponse(product, price, productDetail);
	}

	/**
	 * Adds a new product to the repository.
	 * 
	 * @param product - The product to be added.
	 * @return Product - The saved product object.
	 */
	public Product addProduct(Product product) {
		logger.info("Adding product: {}", product.getName());
		return productRepository.save(product);
	}

	/**
	 * Removes a product from the repository based on its ID.
	 * 
	 * @param id - The ID of the product to be removed.
	 */
	public void removeProduct(Long productId) {
		logger.info("Removing product with ID: {}", productId);
		if (!productRepository.existsById(productId)) {
			throw new ProductNotFoundException("Product with ID " + productId + " not found.");
		}
		productRepository.deleteById(productId);
	}

	/**
	 * Retrieves all products from the repository.
	 * 
	 * @return List<Product> - A list of all available products.
	 */
	public List<Product> getAllProducts() {
		logger.info("Fetching all products");
		return productRepository.findAll();
	}
}
