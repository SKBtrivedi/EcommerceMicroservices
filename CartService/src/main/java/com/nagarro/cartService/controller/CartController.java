package com.nagarro.cartService.controller;

import java.util.List;

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

import com.nagarro.cartService.model.Cart;
import com.nagarro.cartService.service.CartService;

/**
 * CartController is the REST controller responsible for managing all
 * cart-related operations. It handles the creation, retrieval, and deletion of
 * products in a user's cart. This controller exposes endpoints to be consumed
 * by external clients.
 */
@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	/**
	 * Adds a product to the user's cart.
	 * 
	 * @param cart - the cart object that contains the product and quantity
	 *             information.
	 * @return ResponseEntity<Cart> - returns the cart object with HTTP status 201
	 *         CREATED.
	 */
	@Scope("write:products")
	@PostMapping("/add")
	public ResponseEntity<Cart> addProductToCart(@RequestBody Cart cart) {
		Cart itemAdded = cartService.addProductToCart(cart);
		return new ResponseEntity<Cart>(itemAdded, HttpStatus.CREATED);
	}

	/**
	 * Retrieves all items in the cart across all users (for administrative
	 * purposes).
	 * 
	 * @return ResponseEntity<List<Cart>> - returns a list of all cart items.
	 */
	@Scope("write:products")
	@GetMapping("/all")
	public ResponseEntity<List<Cart>> getAllCartItems() {
		return ResponseEntity.ok(cartService.getCartItems());
	}

	/**
	 * Removes a product from the user's cart based on the productId.
	 * 
	 * @param productId - ID of the product to be removed from the cart.
	 * @return ResponseEntity<String> - returns a success message upon successful
	 *         removal.
	 */
	@Scope("write:products")
	@DeleteMapping("/remove/{productId}")
	public ResponseEntity<String> removeProductFromCart(@PathVariable Long productId) {
		cartService.removeProductFromCart(productId);
		return ResponseEntity.ok("Product removed from cart successfully.");
	}
}
