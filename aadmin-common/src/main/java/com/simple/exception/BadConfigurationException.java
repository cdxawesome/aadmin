package com.simple.exception;

/**
 * @author simple
 * @date 2021/12/5
 */
public class BadConfigurationException extends RuntimeException {
    public BadConfigurationException() {
        super();
    }

    public BadConfigurationException(String message) {
        super(message);
    }

    public BadConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadConfigurationException(Throwable cause) {
        super(cause);
    }

    protected BadConfigurationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
