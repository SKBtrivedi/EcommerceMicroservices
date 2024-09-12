package com.nagarro.checkout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.checkout.model.Checkout;
import com.nagarro.checkout.service.CheckoutService;

/**
 * CheckoutController is the REST controller responsible for managing the
 * checkout process. It handles the checkout of cart items and retrieves the
 * order status based on orderId.
 */
@RestController
@RequestMapping("/checkout")
public class CheckoutController {

	@Autowired
	private CheckoutService checkoutService;

	/**
	 * Processes the checkout by taking the cart details and finalizing the order.
	 * 
	 * @param checkout - the checkout object containing cartId and other details.
	 * @return ResponseEntity<Checkout> - returns the checkout object with order
	 *         status and details.
	 */
	@Scope("write:products")
	@PostMapping("/process")
	public ResponseEntity<Checkout> processCheckout(@RequestBody Checkout checkout) {
		Checkout checkOutResponse = checkoutService.checkoutCart(checkout);
		return new ResponseEntity<Checkout>(checkOutResponse, HttpStatus.OK);
	}
}
