package edu.eci.dosw.tech_cup.exception;

/**
 * Exception thrown when a requested resource does not exist in the system.
 *
 * <p>Controllers catch this exception and return {@code 404 Not Found} to the client,
 * distinguishing resource-not-found errors from validation/business-rule errors
 * ({@code 400 Bad Request}).</p>
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
