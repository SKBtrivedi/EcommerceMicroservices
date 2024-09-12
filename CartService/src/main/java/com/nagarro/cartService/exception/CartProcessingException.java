package com.nagarro.cartService.exception;

public class CartProcessingException extends RuntimeException {
    public CartProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
