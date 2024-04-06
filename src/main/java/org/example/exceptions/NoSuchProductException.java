package org.example.exceptions;

public class NoSuchProductException extends RuntimeException {
    public NoSuchProductException(String s) {
        super(s);
    }
}
