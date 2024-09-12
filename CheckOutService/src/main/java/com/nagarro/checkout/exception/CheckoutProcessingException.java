package com.nagarro.checkout.exception;

public class CheckoutProcessingException extends RuntimeException {
    public CheckoutProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}

