package com.nagarro.checkout.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.checkout.model.Checkout;

/**
 * CheckoutRepository is the persistence layer for the Checkout entity. It
 * provides methods to interact with the database for storing and retrieving
 * checkout orders.
 */
public interface CheckoutRepository extends JpaRepository<Checkout, Long> {
}
