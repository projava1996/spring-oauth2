package com.authorizationserver.dto.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.authorizationserver.exception.ErrorCodeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.Date;
import java.util.Set;

@Data
@Builder
@ToString
@AllArgsConstructor
public class HttpResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
    private Date timeStamp;
    private int httpStatusCode; // 200, 201, 400, 500
    private HttpStatus httpStatus;
    private String message;
    private String errorCode;
    private Set<String> errors;


    // Constructor never used. Can be (and should be) deleted
    public HttpResponse() {
        this.timeStamp = new Date();
        this.httpStatusCode = 200;
        this.httpStatus = HttpStatus.OK;
        this.errors = Collections.emptySet();
        this.message = "Success";
    }
    public HttpResponse(ErrorCodeType errorCodeType) {
        this.timeStamp = new Date();
        this.httpStatusCode = 400;
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.errorCode = errorCodeType.getCode();
        this.errors = Set.of(errorCodeType.getMessage());
        this.message = errorCodeType.getMessage();
    }

    public HttpResponse(int httpStatusCode, HttpStatus httpStatus, String message) {
        this.timeStamp = new Date();
        this.httpStatusCode = httpStatusCode;
        this.httpStatus = httpStatus;
        this.errors = null;
        this.message = message;
    }

    public HttpResponse(HttpStatus httpStatus, ErrorCodeType errorCodeType) {
        this.timeStamp = new Date();
        this.httpStatusCode = httpStatus.value();
        this.httpStatus = httpStatus;
        this.errorCode = errorCodeType.getCode();
        this.errors = Set.of(errorCodeType.getMessage());
        this.message = errorCodeType.getMessage();
    }

    public HttpResponse(HttpStatus httpStatus, String message) {
        this.timeStamp = new Date();
        this.httpStatusCode = httpStatus.value();
        this.httpStatus = httpStatus;
        this.errors = null;
        this.message = message;
    }

    public HttpResponse(HttpStatus httpStatus, String error, String message) {
        this.timeStamp = new Date();
        this.httpStatusCode = httpStatus.value();
        this.httpStatus = httpStatus;
        this.errorCode = error;
        this.message = message;
    }
    public HttpResponse(HttpStatus httpStatus, String errorCode, String message, Set<String> errors) {
        this.timeStamp = new Date();
        this.httpStatusCode = httpStatus.value();
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.errors = errors;
        this.message = message;
    }

    public HttpResponse(HttpStatus httpStatus, Set<String> errors, String message) {
        this.timeStamp = new Date();
        this.httpStatusCode = httpStatus.value();
        this.httpStatus = httpStatus;
        this.errors = errors;
        this.message = message;
    }
}
