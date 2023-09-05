package com.nashss.se.musicplaylistservice.exceptions;

/**
 * Exception to throw when a given playlist ID is not found in the database.
 */
public class MealPlanNotFoundException extends RuntimeException {


    private static final long serialVersionUID = 2438635200574007920L;

    /**
     * Exception with no message or cause.
     */
    public MealPlanNotFoundException() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public MealPlanNotFoundException(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public MealPlanNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public MealPlanNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
