package com.authorizationserver.exception;

import java.text.MessageFormat;
import java.util.List;

public class RestBusinessException extends RuntimeException {
    private String message;
    private final ErrorCodeType code;
    private String parameter;
    private List<String> parameters;

    public String getMessage() {
        if (this.message != null) {
            return this.message;
        } else if (this.parameter != null) {
            return MessageFormat.format(this.code.getMessage(), this.parameter);
        } else {
            return this.parameters != null ? MessageFormat.format(this.code.getMessage(), this.parameters.toArray()) : this.code.getMessage();
        }
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getParameter() {
        return this.parameter;
    }

    public List<String> getParameters() {
        return this.parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }

    public RestBusinessException(ErrorCodeType code) {
        this(code, code.getMessage());
    }

    public RestBusinessException(ErrorCodeType code, String message) {
        super(message);
        this.message = message;
        this.code = code;
    }

    public RestBusinessException(ErrorCodeType code, String message, String parameter) {
        super(code.getMessage());
        this.parameter = parameter;
        if (message != null) {
            this.message = message;
        }

        this.code = code;
    }

    public RestBusinessException(ErrorCodeType code, List<String> params) {
        super(code.getMessage());
        this.code = code;
        this.parameters = params;
    }

    public ErrorCodeType getCode() {
        return this.code;
    }

    public String toErrorLog() {
        return this.code + "-" + this.getMessage();
    }
}
