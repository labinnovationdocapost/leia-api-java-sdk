package io.leia;

public class LeiaException extends Exception {
    public LeiaException(String message, Throwable innerException) {
        super(message, innerException);
    }
}
