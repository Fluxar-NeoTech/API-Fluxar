package org.example.apifluxar.exception;

public class EmptyProducts extends RuntimeException {
    public EmptyProducts(String message) {
        super(message);
    }
}
