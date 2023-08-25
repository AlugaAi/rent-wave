package com.alugaai.rentwave.lib.error.exception;

import com.alugaai.rentwave.lib.error.dto.ErrorDto;
import com.google.gson.Gson;

public abstract class ApplicationException extends RuntimeException {

    protected ErrorDto error = new ErrorDto();

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, String payload) {
        super(message);
        error.setMessage(message);
        error.setPayload(payload);
    }

    public ApplicationException(String message, Object payload) {
        super(message);
        error.setMessage(message);
        error.setPayload(payload.toString());
    }

    public ErrorDto getError() {
        return error;
    }

}
