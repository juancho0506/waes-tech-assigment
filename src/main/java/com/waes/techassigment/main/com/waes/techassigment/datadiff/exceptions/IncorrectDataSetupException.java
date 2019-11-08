package com.waes.techassigment.main.com.waes.techassigment.datadiff.exceptions;

public class IncorrectDataSetupException extends RuntimeException{
    public IncorrectDataSetupException() {
        super();
    }

    public IncorrectDataSetupException(String message) {
        super(message);
    }

    public IncorrectDataSetupException(String message, Throwable cause) {
        super(message, cause);
    }
}
