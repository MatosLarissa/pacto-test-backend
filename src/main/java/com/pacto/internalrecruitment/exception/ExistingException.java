package com.pacto.internalrecruitment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ExistingException extends RuntimeException {

    public ExistingException(String message) {
        super(message);
    }
}
