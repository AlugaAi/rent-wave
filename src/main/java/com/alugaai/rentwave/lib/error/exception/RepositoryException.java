package com.alugaai.rentwave.lib.error.exception;

public class RepositoryException extends ApplicationException {
    public RepositoryException(String message) {
        super(message);
    }

    public RepositoryException(String message, String payload) {
        super(message, payload);
    }

    public RepositoryException(String message, Object payload) {
        super(message, payload);
    }

}
