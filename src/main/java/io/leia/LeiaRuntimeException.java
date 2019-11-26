package io.leia;

public class LeiaRuntimeException extends RuntimeException {
//    private final Exception innerException;

    public LeiaRuntimeException(String message, Throwable innerException) {
        super(message, innerException);
//        this.innerException = innerException;
    }
}
