package org.example.apifluxar.exception;

public class EmptyCapacityHistory extends RuntimeException {
    public EmptyCapacityHistory(String message) {
        super(message);
    }
}
