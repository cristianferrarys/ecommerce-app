package tech.between.pvp.exceptions;

public class PricesException extends Exception {
    public PricesException(String message) {
        super(message);
    }

    public PricesException(String message, Throwable cause) {
        super(message, cause);
    }
}

