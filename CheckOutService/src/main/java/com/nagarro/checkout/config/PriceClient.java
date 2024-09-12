package com.nagarro.checkout.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nagarro.checkout.model.Price;

@FeignClient(name = "PRICESERVICE", configuration = FeignConfig.class)
public interface PriceClient {
	@GetMapping("/price/{productId}")
	Price getPriceByProductId(@PathVariable("productId") Long productId);
}
