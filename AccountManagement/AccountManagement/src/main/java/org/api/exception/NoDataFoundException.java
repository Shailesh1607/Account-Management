package org.api.exception;

/**
 * Custom exception to handle cases where no data is found.
 */
public class NoDataFoundException extends RuntimeException {
    public NoDataFoundException(String message) {
        super(message);
    }
}
