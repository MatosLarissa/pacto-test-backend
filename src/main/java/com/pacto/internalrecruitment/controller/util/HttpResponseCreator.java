package com.pacto.internalrecruitment.controller.util;

import com.pacto.internalrecruitment.model.dtos.user.error.HttpErrorReply;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class HttpResponseCreator
{
    protected ResponseEntity<Void> OkResponse()
    {
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    protected <T> ResponseEntity<T> OkResponse(T value)
    {
        return ResponseEntity.status(HttpStatus.OK).body(value);
    }

    protected <T> ResponseEntity<T> CreatedResponse(T value)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(value);
    }

    protected ResponseEntity<Void> NoContentResponse()
    {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    protected ResponseEntity<HttpErrorReply> BadRequestResponse(String[] errors)
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HttpErrorReply(errors));
    }

    protected ResponseEntity<HttpErrorReply> NotFoundResponse(String[] errors)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HttpErrorReply(errors));
    }

    protected ResponseEntity<HttpErrorReply> NotFoundResponse(String error)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HttpErrorReply(error));
    }

    protected ResponseEntity<HttpErrorReply> ConflictResponse(String[] errors)
    {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new HttpErrorReply(errors));
    }

    protected ResponseEntity<HttpErrorReply> UnprocessableEntityResponse(String[] errors)
    {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new HttpErrorReply(errors));
    }

    protected ResponseEntity<HttpErrorReply> InternalServerErrorResponse(String[] errors)
    {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HttpErrorReply(errors));
    }
}
