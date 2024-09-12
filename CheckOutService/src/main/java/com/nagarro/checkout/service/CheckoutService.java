package com.nagarro.checkout.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.checkout.config.PriceClient;
import com.nagarro.checkout.exception.CheckoutProcessingException;
import com.nagarro.checkout.model.Checkout;
import com.nagarro.checkout.model.Price;
import com.nagarro.checkout.repository.CheckoutRepository;

/**
 * CheckoutService is responsible for the business logic related to the checkout
 * process. This service interacts with the CheckoutRepository to perform CRUD
 * operations on orders.
 */
@Service
public class CheckoutService {

	private static final Logger logger = LoggerFactory.getLogger(CheckoutService.class);

	@Autowired
	private CheckoutRepository checkoutRepository;

	@Autowired
	private PriceClient priceClient;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	/**
	 * Processes the checkout by saving the checkout object with cartId, totalPrice,
	 * and order status.
	 * 
	 * @param checkout - the checkout object containing cartId, total price, and
	 *                 other details.
	 * @return Checkout - returns the checkout object that was processed and saved.
	 */
	public Checkout checkoutCart(Checkout checkout) {
		logger.info("Processing checkout for cartId: {}", checkout.getCartId());

		try {
			Price price = priceClient.getPriceByProductId(checkout.getCartId());
			checkout.setTotalPrice(price.getPrice());
			Checkout savedCheckout = checkoutRepository.save(checkout);
			rabbitTemplate.convertAndSend("notificationQueue",
					"Checkout completed for cart ID: " + checkout.getCartId());
			logger.info("Checkout completed for cartId: {}", checkout.getCartId());
			return savedCheckout;
		} catch (Exception exception) {
			logger.error("Error fetching price for cartId: {} from PriceService", checkout.getCartId(), exception);
			throw new CheckoutProcessingException("Error fetching price for product in cartId: " + checkout.getCartId(),
					exception);
		}
	}
}
