package org.example.apifluxar.exception;

public class NotAuthorizedEmployee extends RuntimeException {
    public NotAuthorizedEmployee(String message) {
        super(message);
    }
}
