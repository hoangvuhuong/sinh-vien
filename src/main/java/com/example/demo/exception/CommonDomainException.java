package com.example.demo.exception;


import java.util.List;

import com.example.demo.validator.CommonError;

public class CommonDomainException extends Exception {

    private List<CommonError> errors;
    private int errorCode;

    public CommonDomainException() {
    }

    public CommonDomainException(String message) {
        super(message);
    }

    public CommonDomainException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonDomainException(Throwable cause) {
        super(cause);
    }

    public CommonDomainException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CommonDomainException(int code, String message) {
        super(message);
        this.errorCode = code;
    }

    public CommonDomainException(List<CommonError> errors) {
        super();
        this.errors = errors;
    }
}
