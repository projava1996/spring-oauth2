package com.authorizationserver.exception;

import com.mysql.cj.util.StringUtils;
import com.authorizationserver.dto.base.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@Slf4j
public class GlobalHandleException {
    private static final String ACCOUNT_LOCKED = "Your account has been locked. Please contact administration";
    private static final String METHOD_IS_NOT_ALLOWED = "This request method is not allowed on this endpoint. Please send a '%s' request";
    private static final String INTERNAL_SERVER_ERROR_MSG = "An error occurred while processing the request";
    private static final String INCORRECT_CREDENTIALS = "Username / password incorrect. Please try again";
    private static final String ACCOUNT_DISABLED = "Your account has been disabled. If this is an error, please contact administration";
    private static final String ERROR_PROCESSING_FILE = "Error occurred while processing file";
    private static final String NOT_ENOUGH_PERMISSION = "You do not have enough permission";
    private static final String PAYLOAD_INVALID = "Please check again: there is some field invalid in request";

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<HttpResponse> methodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        HttpMethod supportedMethod = Objects.requireNonNull(exception.getSupportedHttpMethods()).iterator().next();
        return createHttpResponse(METHOD_NOT_ALLOWED, String.format(METHOD_IS_NOT_ALLOWED, supportedMethod));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpResponse> internalServerErrorException(Exception exception) {
        log.error(exception.getMessage());
        log.info("Exception handle - :{}", exception);
        return createHttpResponse(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MSG);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<HttpResponse> handlerAuthenticationException(AuthenticationException exception) {
        log.error(exception.getMessage());
        log.info("Exception handle - :{}", exception);
        return createHttpResponse(UNAUTHORIZED, INTERNAL_SERVER_ERROR_MSG);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HttpResponse> handlerHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        log.error(exception.getMessage());
        log.info("HttpMessageNotReadableException handle - :{}", exception.getMessage(), exception);
        return createHttpResponse(BAD_REQUEST, PAYLOAD_INVALID);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<HttpResponse> internalServerErrorException(MissingServletRequestParameterException exception) {
        log.error(exception.getMessage());
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HttpResponse> handleConstraintViolationException(MethodArgumentNotValidException exception) {
        log.error(exception.getMessage());
        Set<String> errors = new HashSet<>();
        String message = exception.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : exception.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        return createHttpResponseAndReason(message, errors);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<HttpResponse> handleConstraintViolationException(MethodArgumentTypeMismatchException exception) {
        log.error(exception.getMessage());
        Set<String> errors = new HashSet<>();
        errors.add(String.format("%s - %s", exception.getErrorCode(), exception.getName()));
        String message = String.format("Invalid field(parameter): %s", exception.getName());
        return createHttpResponseAndReason(message, errors);
    }

    @ExceptionHandler({RestBusinessException.class})
    public ResponseEntity<HttpResponse> handleRestErrors(RestBusinessException ex) {
        log.error(ex.getMessage());
        if (ex.getCode() != null && StringUtils.isNullOrEmpty(ex.getCode().getMessage())) {
            return createHttpResponseWithErrorCodeType(ex.getCode());
        } else {
            return createHttpResponse(ex.getCode().getCode(), ex.getMessage(), Set.of());
        }
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<HttpResponse> handleConstraintViolationException(ConstraintViolationException exception) {
        log.error(exception.getMessage());
        Set<String> errors = new HashSet<>();
        String message = exception.getMessage();
        for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName() + " " +
                    violation.getPropertyPath() + ": " + violation.getMessage());
        }

        return createHttpResponseAndReason(message, errors);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<HttpResponse> badCredentialsException() {
        return createHttpResponse(BAD_REQUEST, INCORRECT_CREDENTIALS);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<HttpResponse> accessDeniedException() {
        return createHttpResponse(FORBIDDEN, NOT_ENOUGH_PERMISSION);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<HttpResponse> responseStatusException(ResponseStatusException ex) {
        return createHttpResponse(FORBIDDEN, NOT_ENOUGH_PERMISSION);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<HttpResponse> iOException(IOException exception) {
        log.error(exception.getMessage());
        return createHttpResponse(INTERNAL_SERVER_ERROR, ERROR_PROCESSING_FILE);
    }

    private ResponseEntity<HttpResponse> createHttpResponse(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus,
                httpStatus.getReasonPhrase().toUpperCase(), message), httpStatus);
    }


    private ResponseEntity<HttpResponse> createHttpResponse(String errorCode, String message, Set<String> reason) {
        return new ResponseEntity<>(new HttpResponse(HttpStatus.BAD_REQUEST,
                errorCode, message, reason), HttpStatus.BAD_REQUEST);
    }
    private ResponseEntity<HttpResponse> createHttpResponseAndReason(String message, Set<String> reason) {
        return new ResponseEntity<>(new HttpResponse(HttpStatus.BAD_REQUEST,
                reason, message), HttpStatus.BAD_REQUEST);
    }


    private ResponseEntity<HttpResponse> createHttpResponseWithErrorCodeType(ErrorCodeType errorCodeType) {
        return new ResponseEntity<>(new HttpResponse(HttpStatus.BAD_REQUEST, errorCodeType), HttpStatus.BAD_REQUEST);
    }
}
