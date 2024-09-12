package com.nagarro.product.dto;

import com.nagarro.product.model.Product;

public class ProductResponse {
	private Product product;
	private Price price;
	private ProductDetail productDetail;

	// Constructor
	public ProductResponse(Product product, Price price, ProductDetail productDetail) {
		this.product = product;
		this.price = price;
		this.productDetail = productDetail;
	}

	// Getters and Setters
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public ProductDetail getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(ProductDetail productDetail) {
		this.productDetail = productDetail;
	}
}
