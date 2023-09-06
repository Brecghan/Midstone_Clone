package com.nashss.se.musicplaylistservice.exceptions;

/**
 * Exception to throw when a given playlist ID is not found in the database.
 */
public class PantryNotFoundException extends RuntimeException {


    private static final long serialVersionUID = -5177851288328863886L;

    /**
     * Exception with no message or cause.
     */
    public PantryNotFoundException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public PantryNotFoundException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public PantryNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public PantryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
