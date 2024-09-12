package com.nagarro.cartService.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nagarro.cartService.exception.CartProcessingException;
import com.nagarro.cartService.model.Cart;
import com.nagarro.cartService.repository.CartRepository;

/**
 * CartService is responsible for the business logic related to cart management.
 * This service interacts with the CartRepository to perform CRUD operations on
 * the cart.
 */
@Service
public class CartService {

	private static final Logger logger = LoggerFactory.getLogger(CartService.class);

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	/**
	 * Adds a product to the user's cart. If the product is already in the cart, it
	 * should be updated accordingly.
	 * 
	 * @param cart - the cart object containing the product and quantity to be
	 *             added.
	 * @return Cart - returns the cart object that was added.
	 */
	public Cart addProductToCart(Cart cart) {
		logger.info("Saving or updating cart with ID: {}", cart.getId());
		try {
			Cart savedCart = cartRepository.save(cart);
			rabbitTemplate.convertAndSend("notificationQueue", "Product added to cart");
			return savedCart;
		} catch (Exception ex) {
			logger.error("Error occurred while saving or updating cart with ID: {}", cart.getId(), ex);
			throw new CartProcessingException("Error saving or updating cart", ex);
		}
	}

	/**
	 * Retrieves all cart items across all users.
	 * 
	 * @return List<Cart> - a list of all cart items.
	 */
	public List<Cart> getCartItems() {
		logger.info("Fetching cart");
		return cartRepository.findAll();
	}

	/**
	 * Removes a product from the user's cart by productId.
	 * 
	 * @param productId - ID of the product to be removed.
	 */
	public void removeProductFromCart(Long productId) {
		Cart cartItem = cartRepository.findByProductId(productId);
		if (cartItem != null) {
			cartRepository.delete(cartItem);
		}
	}
}
