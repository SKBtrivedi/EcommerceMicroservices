package com.nagarro.priceService.controller;

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

import com.nagarro.priceService.model.Price;
import com.nagarro.priceService.service.PriceService;

/**
 * PriceController handles all operations related to product pricing. It allows
 * adding, updating, retrieving, and deleting price information.
 */
@RestController
@RequestMapping("/price")
public class PriceController {

	@Autowired
	private PriceService priceService;

	/**
	 * Retrieves price details for a specific product based on the productId.
	 * 
	 * @param productId - The ID of the product to retrieve the price for.
	 * @return ResponseEntity<Price> - Returns the product price with HTTP status
	 *         200.
	 */
	@Scope("read:products")
	@GetMapping("/{productId}")
	public ResponseEntity<Price> getPriceByProductId(@PathVariable Long productId) {
		return ResponseEntity.ok(priceService.getPriceByProductId(productId));
	}

	/**
	 * Adds or updates the price for a product.
	 * 
	 * @param price - The price object containing productId and the price value.
	 * @return ResponseEntity<Price> - Returns the updated price with HTTP status
	 *         201.
	 */
	@Scope("write:products")
	@PostMapping("/add")
	public ResponseEntity<Price> addOrUpdatePrice(@RequestBody Price price) {
		return new ResponseEntity<>(priceService.addOrUpdatePrice(price), HttpStatus.CREATED);
	}

	/**
	 * Removes price information for a product based on the productId.
	 * 
	 * @param productId - The ID of the product whose price should be removed.
	 * @return ResponseEntity<String> - Returns a success message upon removal.
	 */
	@Scope("write:products")
	@DeleteMapping("/remove/{productId}")
	public ResponseEntity<String> removePrice(@PathVariable Long productId) {
		priceService.removePrice(productId);
		return ResponseEntity.ok("Price removed successfully");
	}
}
