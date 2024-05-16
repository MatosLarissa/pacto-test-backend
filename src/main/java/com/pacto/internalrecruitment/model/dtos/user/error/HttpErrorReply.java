package com.pacto.internalrecruitment.model.dtos.user.error;

public class HttpErrorReply {
    public String[] errorMessages;

    public HttpErrorReply(String[] errorMessages) {
        this.errorMessages = errorMessages;
    }

    public HttpErrorReply(String errorMessage) {
        this.errorMessages = new String[] {errorMessage};
    }
}