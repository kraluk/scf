package com.kraluk.scf.server.core.exception;

/**
 * SCF specific RuntimeException
 *
 * @author lukasz
 */
public class ScfRuntimeException extends RuntimeException {

    public ScfRuntimeException() {
    }

    public ScfRuntimeException(String message) {
        super(message);
    }

    public ScfRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ScfRuntimeException(Throwable cause) {
        super(cause);
    }

    public ScfRuntimeException(String message, Throwable cause, boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
