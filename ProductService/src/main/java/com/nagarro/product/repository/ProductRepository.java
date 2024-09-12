package com.nagarro.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.product.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
