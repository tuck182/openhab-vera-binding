package org.openhab.binding.vera.internal;

public class VeraClientException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public VeraClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
