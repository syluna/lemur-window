package com.jayfella.lemur.window.exception;

public class WindowNotInitializedException extends RuntimeException {

    public WindowNotInitializedException() {
    }

    public WindowNotInitializedException(String message) {
        super(message);
    }

    public WindowNotInitializedException(String message, Throwable cause) {
        super(message, cause);
    }

    public WindowNotInitializedException(Throwable cause) {
        super(cause);
    }

    public WindowNotInitializedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
