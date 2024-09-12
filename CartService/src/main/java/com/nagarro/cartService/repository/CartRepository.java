package com.nagarro.cartService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.cartService.model.Cart;

/**
 * CartRepository is the persistence layer for the Cart entity. It provides
 * methods to interact with the database for storing and retrieving cart items.
 */
public interface CartRepository extends JpaRepository<Cart, Long> {
	/**
	 * Finds a cart item by its productId.
	 * 
	 * @param productId - the product ID to search for.
	 * @return Cart - returns the cart object if found.
	 */
	Cart findByProductId(Long productId);
}
