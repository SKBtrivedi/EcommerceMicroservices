package com.nagarro.product.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nagarro.product.dto.ProductDetail;

@FeignClient(name = "ProductDetailService",configuration = FeignConfig.class)
public interface ProductDetailClient {
    @GetMapping("/product-detail/{productId}")
    ProductDetail getProductDetailByProductId(@PathVariable("productId") Long productId);
}

