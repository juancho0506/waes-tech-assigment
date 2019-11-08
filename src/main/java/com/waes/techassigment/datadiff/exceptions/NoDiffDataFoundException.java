package com.waes.techassigment.datadiff.exceptions;

public class NoDiffDataFoundException extends RuntimeException{
    public NoDiffDataFoundException() {
        super();
    }

    public NoDiffDataFoundException(String message) {
        super(message);
    }

    public NoDiffDataFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
