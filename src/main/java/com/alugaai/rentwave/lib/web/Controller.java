package com.alugaai.rentwave.lib.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface Controller {

    default <T> ResponseEntity<T> success(T content) {
        return response(HttpStatus.OK, content);
    }

    default <T> ResponseEntity<T> created(T content) {
        return response(HttpStatus.CREATED, content);
    }

    default <T> ResponseEntity<T> notFound(T content) {
        return response(HttpStatus.NOT_FOUND, content);
    }

    default <T> ResponseEntity<T> badRequest(T content) {
        return response(HttpStatus.BAD_REQUEST, content);
    }

    private <T> ResponseEntity<T> response(HttpStatus httpStatus, T content) {
        return ResponseEntity.status(httpStatus.value()).body(content);
    }

}
