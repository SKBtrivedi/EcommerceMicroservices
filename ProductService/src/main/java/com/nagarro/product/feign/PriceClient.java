package com.nagarro.product.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nagarro.product.dto.Price;

@FeignClient(name = "PriceService", configuration = FeignConfig.class)
public interface PriceClient {
    @GetMapping("/price/{productId}")
    Price getPriceByProductId(@PathVariable("productId") Long productId);
}

